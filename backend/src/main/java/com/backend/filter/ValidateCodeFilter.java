package com.backend.filter;

import com.backend.controller.ValidateController;
import com.backend.exception.ValidateCodeException;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

//    @Resource
//    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if ("/form".equalsIgnoreCase(httpServletRequest.getRequestURI())
                && "post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            try {
                HttpSession session = httpServletRequest.getSession();
                String codeInReq = httpServletRequest.getParameter("imageCode");
                validateCode(session,codeInReq);
            } catch (ValidateCodeException e) {
//                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(HttpSession session, String codeInRequest) throws ServletRequestBindingException {
        String codeInSession = (String)session.getAttribute(ValidateController.SESSION_KEY_IMAGE_CODE);

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