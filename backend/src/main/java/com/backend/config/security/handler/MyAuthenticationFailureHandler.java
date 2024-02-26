package com.backend.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: qxd
 * @date: 2024/2/23
 * @description:
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        /*
          登录失败异常处理
         */
        log.info("登录失败，验证码错误");
        // 设置返回的状态码为401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置返回的内容类型为JSON
        response.setContentType("application/json;charset=UTF-8");
        // 将异常信息返回给客户端
        response.getWriter().write(exception.getMessage());
        response.getWriter().flush();
    }
}
