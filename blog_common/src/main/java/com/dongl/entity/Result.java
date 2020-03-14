package com.dongl.entity;

import lombok.Data;

/**
 * @Description: 数据统一返回格式化
 * @author: YaoGuangXun
 * @date: 2020/3/13 21:24
 * @Version: 1.0
 */

@Data
public class Result {

    private boolean flag;

    private Integer code;

    private String message;

    private Object data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static Result success() {
        return new Result(true, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


    public static Result success(String msg) {
        return new Result(true, ResultEnum.SUCCESS.getCode(), msg);
    }

    public static Result success(Object data) {
        return new Result(true, ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(), data);
    }

    public static Result success(String msg, Object data) {
        return new Result(true, ResultEnum.ERROR.getCode(), msg, data);
    }


    public static Result error() {
        return new Result(true, ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }


    public static Result error(String msg) {
        return new Result(true, ResultEnum.ERROR.getCode(), msg);
    }

    public static Result error(Object data) {
        return new Result(true, ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg(), data);
    }

    public static Result error(String msg, Object data) {
        return new Result(true, ResultEnum.ERROR.getCode(), msg, data);
    }

    public static Result exception(String msg) {
        return new Result(false, ResultEnum.EXCEPTION.getCode(), msg);
    }





}
