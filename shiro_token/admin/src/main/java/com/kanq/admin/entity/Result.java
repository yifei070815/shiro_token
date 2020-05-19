package com.kanq.admin.entity;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result ok(int code, String message,Object data) {
        return new Result(200,message, data);
    }

    public static Result ok( String message) {
        return new Result(message);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok() {
        return new Result(null);
    }

    public static Result errorMsg(String message) {
        return new Result(500, message, null);
    }

    //抛出异常时会调用这个方法
    public static Result errorException(String msg) {
        return new Result(555, msg, null);
    }

}
