package com.backend.controller;
import com.backend.common.core.response.BlogResponse;
import com.backend.service.AuthorizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/auth")
@Api(value = "登录认证",tags = {"登录认证"})
public class AuthorizeController {

    //正则表达式
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private final String USERNAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{4,16}$";

    @Resource
    AuthorizeService service;


    //邮箱验证
    @PostMapping("/valid-register-email")
    @ApiOperation(value = "邮箱验证",notes = "验证登录的邮箱")
    public BlogResponse validateRegisterEmail(@Pattern (regexp = EMAIL_REGEX )@RequestParam("email") String email, HttpSession session){

        System.out.println("这里");
        String s =  service.sendValidateEmail(email,session.getId(),false);

        if(s == null)
            return BlogResponse.success("邮件已发送,请注意查收");
        else
            return BlogResponse.error(400,s);
    }

    @PostMapping("/valid-reset-email")
    @ApiOperation(value = "获取验证码",notes = "通过邮箱获取验证码")
    public BlogResponse validateResetEmail(@Pattern (regexp = EMAIL_REGEX )@RequestParam("email") String email, HttpSession session){
        String s =  service.sendValidateEmail(email,session.getId(),true);
        if(s == null)
            return BlogResponse.success("邮件已发送,请注意查收");
        else
            return BlogResponse.error(400,s);
    }


    //用户注册
    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "注册新账户")
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
    @ApiOperation(value = "用户注册",notes = "注册新账户")
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
    @ApiOperation(value = "重置密码",notes = "重置账户密码")
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