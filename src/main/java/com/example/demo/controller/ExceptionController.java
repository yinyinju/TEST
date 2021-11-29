package com.example.demo.controller;

import com.example.demo.commonResult.CommonResult;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    //捕捉shiro的异常

    @ExceptionHandler(ShiroException.class)
    public CommonResult handle401()
    {
        return CommonResult.forbidden("没有权限访问");
    }

    @ExceptionHandler(Exception.class)
    public CommonResult golbalException(HttpServletRequest request,Throwable ex)
    {
        return CommonResult.failed("404");
    }
}
