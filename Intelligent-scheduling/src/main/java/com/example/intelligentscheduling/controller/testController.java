package com.example.intelligentscheduling.controller;/*
 *
 * @Param
 */

import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

    /**
     * 侧式
     */
    @GetMapping
    public R<User> test(){
        System.out.println("success");
        return R.error("error");
    }
}
