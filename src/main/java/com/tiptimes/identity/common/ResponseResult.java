package com.tiptimes.identity.common;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {

    private static final String SUCCESS_MSG = "成功";

    private Integer code;
    private String message;
    private T data = null;

    public ResponseResult() {
    }

    private ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseCodeEnums.SUCCESS.getCode(), SUCCESS_MSG);
    }

    public static <T> ResponseResult success(String message) {
        return new ResponseResult<T>(ResponseCodeEnums.SUCCESS.getCode(), message);
    }

    public static <T> ResponseResult successWithData(T data) {
        return new ResponseResult<T>(ResponseCodeEnums.SUCCESS.getCode(), SUCCESS_MSG, data);
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult(ResponseCodeEnums.FAILURE.getCode(), msg);
    }

    public static ResponseResult error(Integer code, String msg) {
        return new ResponseResult(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
