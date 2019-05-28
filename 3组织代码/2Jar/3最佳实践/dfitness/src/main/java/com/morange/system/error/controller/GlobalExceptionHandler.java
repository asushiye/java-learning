package com.morange.system.error.controller;

import com.morange.system.error.exception.GlobalException;
import com.morange.system.result.Result;
import com.morange.system.result.ResultUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : zhenyun.su
 * @since : 2018-08-03
 * @Comment: 拦截全局异常
 * @ExceptionHandler 该注解表示要处理哪些异常
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> handler(Exception e) {
        if(e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return ResultUtils.failure(exception.getCode(), exception.getMessage(), exception.getData());
        }else {
            return ResultUtils.failure(-1,"spring ioc catch exception", e.getMessage());
        }
    }
}
