package com.dongl.exception;

import com.dongl.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:  全局异常捕获
 * @author: YaoGuangXun
 * @date: 2020/3/14 18:48
 * @Version: 1.0
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.exception(e.getMessage());
    }
}
