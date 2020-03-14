package com.dongl.entity;

public enum ResultEnum {
    SUCCESS(20000, "成功"),
    ERROR(20001, "失败"),
    LOGIN_ERROR(20002, "用户名密码错误"),
    ACCESS_ERROR(20003, "权限不足"),
    REMOTE_ERROR(20004, "远程调用失败"),
    REP_ERROR(20005, "重复操作");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}