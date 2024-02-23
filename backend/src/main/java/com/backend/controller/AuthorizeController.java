package com.backend.controller;
import com.backend.common.core.response.BlogResponse;
import com.backend.entity.AccountEntity;
import com.backend.param.auth.LoginParam;
import com.backend.service.AccountService;
import com.backend.service.AuthorizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.backend.controller.ValidateController.SESSION_KEY_IMAGE_CODE;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/auth")
@Tag(name = "登录认证")
public class AuthorizeController {

    //正则表达式
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private final String USERNAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{4,16}$";

    @Resource
    AuthorizeService service;

    @Resource
    AccountService accountService;

    /**
     * 登录接口
     * @param param
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录",description = "用户登录")
    public BlogResponse login(@Valid LoginParam param,HttpSession session){
        try{
            AccountEntity entity = accountService.findAccountByNameOrEmail(param.getUsername(),null);
            if(session.getAttribute(SESSION_KEY_IMAGE_CODE) != param.getCode()){
                return BlogResponse.error("验证码错误！");
            }
            if(entity != null){
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if(entity.getPassword() == bCryptPasswordEncoder.encode(param.getPassword())){
                    return BlogResponse.success("登录成功！");
                }
            }
            return BlogResponse.error("用户名或密码错误！");
        }catch (Exception e){
            log.info(e.getMessage());
            return BlogResponse.error("系统错误！");
        }
    }

    @PostMapping("/valid-register-email")
    @Operation(summary = "注册-验证邮箱",description = "用户注册时验证邮箱")
    public BlogResponse validateRegisterEmail(@Pattern (regexp = EMAIL_REGEX )@RequestParam("email") String email, HttpSession session){
        String s =  service.sendValidateEmail(email,session.getId(),false);

        if(s == null)
            return BlogResponse.success("邮件已发送,请注意查收");
        else
            return BlogResponse.error(400,s);
    }

    @PostMapping("/valid-reset-email")
    @Operation(summary = "重置密码验证邮箱",description = "重置密码时验证邮箱")
    public BlogResponse validateResetEmail(@Pattern (regexp = EMAIL_REGEX )@RequestParam("email") String email, HttpSession session){
        String s =  service.sendValidateEmail(email,session.getId(),true);
        if(s == null)
            return BlogResponse.success("邮件已发送,请注意查收");
        else
            return BlogResponse.error(400,s);
    }


    @PostMapping("/register")
    @Operation(summary = "用户注册",description = "注册新账户")
    public BlogResponse registerUser(@Pattern(regexp = USERNAME_REGEX)@RequestParam("username") String username,
                                     @Length(min = 6,max = 16) @RequestParam("password") String password,
                                     @RequestParam("email") String email,
                                     @Length(min = 6,max = 6) @RequestParam("code") String code,
                                     HttpSession session){
        String s = service.validateAndRegister(username,password,email,code,session.getId());
        if(s == null){
            return BlogResponse.success("注册成功");
        }else {
            return BlogResponse.error(400, s);
        }
    }

    @PostMapping("/start-rest")
    @Operation(summary = "重置密码",description = "重置密码")
    public BlogResponse startRest(@RequestParam("email") String email,
                                  @Length(min = 6,max = 6) @RequestParam("code") String code,
                                  HttpSession session){
        String s = service.validateOnly(email,code,session.getId());
        if(s == null){
            session.setAttribute("rest-password",email);
            return BlogResponse.success();
        }else {
            return BlogResponse.error(400,s);
        }
    }

    @PostMapping("/do-rest")
    @Operation(summary = "重置密码",description = "重置账户密码")
    public BlogResponse restPassword(@Length(min = 6,max = 16) @RequestParam("password") String password, HttpSession session){
        String email = (String)session.getAttribute("rest-password");
        if(email == null){
            return BlogResponse.error(401,"请先进行邮箱验证");
        }else if(service.resetPassword(password,email)){
            session.removeAttribute("reset-password");
            return BlogResponse.success("密码重置成功");
        }else{
            return BlogResponse.error(500,"内部错误，请联系管理员");
        }
    }

}