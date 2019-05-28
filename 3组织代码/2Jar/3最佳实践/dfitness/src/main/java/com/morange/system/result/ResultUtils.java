package com.morange.system.result;

public class ResultUtils {

    private final static String DEFAULT_SUCCESS_MSG = "success";
    private final static String DEFAULT_FAILURE_MSG = "failure";
    private final static Integer DEFAULT_SUCCESS_CODE = 1;
    private final static Integer DEFAULT_FAILURE_CODE = -1;

    public static Result success() {
        return new Result(true, ResultUtils.DEFAULT_SUCCESS_CODE, ResultUtils.DEFAULT_SUCCESS_MSG);
    }

    public static Result success(Object data) {
        return new Result(true, ResultUtils.DEFAULT_SUCCESS_CODE, ResultUtils.DEFAULT_SUCCESS_MSG, data);
    }

    public static Result success(String msg, Object data) {
        return new Result(true, ResultUtils.DEFAULT_SUCCESS_CODE, msg, data);
    }

    public static Result success(Integer code, Object data) {
        return new Result(true, code, ResultUtils.DEFAULT_SUCCESS_MSG, data);
    }

    public static Result success(Integer code, String msg, Object data) {
        return new Result(true, code, msg, data);
    }

    public static Result failure() {
        return new Result(false, ResultUtils.DEFAULT_FAILURE_CODE, ResultUtils.DEFAULT_FAILURE_MSG);
    }

    public static Result failure(Integer code) {
        return new Result(false, code, ResultUtils.DEFAULT_FAILURE_MSG);
    }

    public static Result failure(Integer code, String msg) {
        return new Result(false, code, msg);
    }

    public static Result failure(String msg, Object data) {
        return new Result(false, -1, msg, data);
    }

    public static Result<Object> failure(Integer code, String msg, Object data) {
        return new Result(false, code, msg, data);
    }

    public static Result result(Boolean status, Integer code, String msg, Object data) {
        return new Result(status, code, msg, data);
    }
}
