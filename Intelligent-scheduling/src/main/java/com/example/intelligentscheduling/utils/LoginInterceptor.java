package com.example.intelligentscheduling.utils;/*
 *
 * @Param
 */

import com.example.intelligentscheduling.common.BaseContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，同时需要在config配置拦截器才能生效
 */
public class LoginInterceptor implements HandlerInterceptor {

    //前置拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断ThreadLocal中是否有用户
        if(BaseContext.getCurrentId() == null){
            response.setStatus(401);
            return false;
        }
        // TODO 8 放行
        return true;

    }



}
