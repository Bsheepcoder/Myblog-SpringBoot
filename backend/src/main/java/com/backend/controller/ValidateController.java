package com.backend.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
@Slf4j
@RestController
@Tag(name = "验证接口")
public class ValidateController {
    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    @GetMapping("/code/image")
    @Operation(summary = "生成验证码",description = "生成验证码")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置response响应
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(100, 38, 4, 20);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(SESSION_KEY_IMAGE_CODE, captcha.getCode());
        String userIdentifier = request.getParameter("userIdentifier");
        log.info("本次生成的验证码为：" + captcha.getCode() + ",已存放到HttpSession中");
        HttpSession session = request.getSession();

        String sessionId = session.getId();
        String codeInSession = (String)session.getAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);
        log.info("验证session验证码：" + codeInSession + ",sessionID为：" + sessionId);
        //图形验证码写出，可以写出到文件，也可以写出到流
        //输出浏览器
        OutputStream out= response.getOutputStream();
        captcha.write(out);
        out.flush();
        out.close();
    }
}
