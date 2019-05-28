package com.morange.system.error.exception;

/**
 * @author : zhenyun.su
 * @since : 2018/9/13
 * @Comment: 自定义全局异常工具类
 */

public class GlobalExceptionUtils {

    public static void throwException(final Integer code, final String msg, Object data) throws GlobalException{
        throw new GlobalException(code, msg, data);
    }

    public static void throwException(final Integer code, final String msg) throws GlobalException{
        throw new GlobalException(code, msg);
    }

    public static void throwException(final String msg) throws GlobalException{
        throw new GlobalException(msg);
    }
}
