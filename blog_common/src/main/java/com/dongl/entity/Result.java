package com.dongl.entity;

import lombok.Data;

/**
 * @Description:  数据统一返回格式化
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


}
