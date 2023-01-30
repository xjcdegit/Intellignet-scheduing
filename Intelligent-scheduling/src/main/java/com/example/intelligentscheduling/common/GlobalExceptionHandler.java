package com.example.intelligentscheduling.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/*
 * 全局异常处理
 * @Param
 */
//指定拦截哪些Controller的异常 如拦截带有RestController注解的
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    //拦截哪些异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception){
        log.error(exception.getMessage());

        if(exception.getMessage().contains("Duplicate entry")){
            String[] s = exception.getMessage().split(" ");
            String msg = s[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }

    //拦截哪些异常
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException exception){
        log.error(exception.getMessage());
        return R.error(exception.getMessage());
    }
}
