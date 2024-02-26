package com.backend.config.security.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class ValidateCodeException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 5022575393500654458L;

    public ValidateCodeException(String message) {
        super(message);
    }
}