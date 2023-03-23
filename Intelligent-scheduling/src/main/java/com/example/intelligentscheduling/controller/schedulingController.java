package com.example.intelligentscheduling.controller;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.Scheduling;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class schedulingController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/{id}")
    public R schedulingById(@PathVariable int id){
        LambdaQueryWrapper<Staff> law = new LambdaQueryWrapper<>();
        law.eq(Staff::getShopId,id);
        //获取该店中的所有店员
        List<Staff> list = staffService.list(law);

        /**
         * 重点：实现智能排班
         */

        //获取排班结果
        List<Scheduling> result = new ArrayList<>();
        return R.success(result);
    }
}
