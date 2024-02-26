package com.backend.config.security.filter;

import com.backend.config.security.exception.ValidateCodeException;
import com.backend.controller.ValidateController;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    /**
     * 登录失败处理器
     */
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //拦截请求链接
        if ("/api/auth/login".equalsIgnoreCase(httpServletRequest.getRequestURI())
                && "post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            try {
                HttpSession session = httpServletRequest.getSession();
                String sessionId = session.getId();
                String codeInReq = httpServletRequest.getParameter("imageCode");
                log.info("sessionID为：" + sessionId + ",请求的验证码：" + codeInReq);
                validateCode(session,codeInReq);
            } catch (ValidateCodeException e) {

                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(HttpSession session, String codeInRequest) {
        String codeInSession = (String)session.getAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);
        log.info("验证的验证码：" + codeInSession);
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if (!codeInRequest.equalsIgnoreCase(codeInSession)) {
            throw new ValidateCodeException("验证码不正确！");
        }
        session.removeAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);
    }
}