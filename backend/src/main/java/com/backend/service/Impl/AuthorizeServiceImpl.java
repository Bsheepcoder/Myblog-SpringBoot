package com.backend.service.Impl;

import com.backend.entity.AccountEntity;
import com.backend.mapper.AccountMapper;
import com.backend.service.AuthorizeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorizeServiceImpl implements AuthorizeService {

    @Value("${spring.mail.username}")
    String from;


    //引入mapper
    @Resource
    AccountMapper accountMapper;

    //引入redis
    @Resource
    StringRedisTemplate template;

    @Resource
    MailSender mailSender;


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if(username == null){
            throw new UsernameNotFoundException("user name can't be null");
        }
        AccountEntity accountEntity = accountMapper.findAccountByNameOrEmail(username,null);
        if(accountEntity == null){
            throw new UsernameNotFoundException("username or password is fault");
        }
        return User.withUsername(accountEntity.getUsername()).password(accountEntity.getPassword()).roles("USERS").build();
    }


    //邮箱验证实现
    @Override
    public String sendValidateEmail(String email,String sessionId,boolean hasAccount){
        /*
         *  1.先生成应用的验证码
         *  2.把邮箱和对应的验证码直接放到Redis里面（过期时间3分钟，如果此时重新要求发邮件，那么只要剩余时间低于2分钟，就可以重新发一次，重复此流程）
         *  3.发送验证码到指定邮箱
         *  4.如果发送失败，把redis里面的刚插入的数据删除
         *  5.用户在注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
         */
        String key = "email:" + sessionId + ":" + email + ":"+hasAccount;
        if(Boolean.TRUE.equals(template.hasKey(key))){
            Long expire = Optional.ofNullable(template.getExpire(key,TimeUnit.SECONDS)).orElse(0L);
            if(expire > 120) return "请求频繁，请稍后再试";
        }
        AccountEntity accountEntity = accountMapper.findAccountByNameOrEmail(null,email);

        if(hasAccount  && accountEntity == null)
            return "此邮箱没有注册";
        if(!hasAccount && accountEntity != null){
            return "此邮箱已被其他用户注册";
        }

        Random random  = new Random();
        int code =random.nextInt(999999);
        //创建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("您好，欢迎注册物流中台系统，验证码为：" + code);
        try{
            //发送邮件
            mailSender.send(message);
            template.opsForValue().set(key,String.valueOf(code),3,TimeUnit.MINUTES);
            return null;
        }  catch (MailException e){
            e.printStackTrace();
        }
        return "邮件发送失败，请联系管理员，检查邮件地址是否有效";
    }

    @Override
    public String validateAndRegister(String username,String password,String email,String code,String sessionId){
        String key = "email:" + sessionId + ":" + email + ":false";

        if(Boolean.TRUE.equals(template.hasKey(key))){
            String s = template.opsForValue().get(key);

            if(s == null) return "验证码失效，请重新请求";
            if(s.equals(code)){
                //清除redis的code
                template.delete(key);
                AccountEntity accountEntity = accountMapper.findAccountByNameOrEmail(username,null);
                password = encoder.encode(password); //加密
                if(accountEntity != null) return "此用户名已被注册，请更换用户名";
                if(accountMapper.createAccount(username,password,email)){
                    return null;
                }else{
                    return "内部错误，请联系管理员";
                }
            }else{
                return "验证码错误，请检查后再提交";
            }
        }else{
            return "请先验证邮箱";
        }
    }

    @Override
    public String validateOnly(String email,String code,String sessionId){
        String key = "email:" + sessionId + ":" + email + ":true";
        if(Boolean.TRUE.equals(template.hasKey(key))){
            String s = template.opsForValue().get(key);
            if(s == null) return "验证码失效，请重新请求";
            if(s.equals(code)){
                template.delete(key);
                return null;
            }else{
                return "验证码错误，请检查后再提交";
            }
        }else{
            return "请先验证邮箱";
        }
    }

    @Override
    public boolean resetPassword(String password, String email){
        password = encoder.encode(password);
        return accountMapper.resetPasswordByEmail(password,email);
    }
}