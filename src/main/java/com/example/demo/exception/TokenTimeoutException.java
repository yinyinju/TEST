package com.example.demo.exception;

import org.apache.shiro.authc.AuthenticationException;

public class TokenTimeoutException extends AuthenticationException {
    private static  final  long serialVersionUID=-8313101744886192005L;

    public TokenTimeoutException(String messag)
    {
        super(messag);
    }
}
