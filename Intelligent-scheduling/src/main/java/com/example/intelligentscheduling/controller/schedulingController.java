package com.example.intelligentscheduling.controller;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.Scheduling;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.service.SchedulingService;
import com.example.intelligentscheduling.service.StaffService;
import com.example.intelligentscheduling.utils.scheduling.main;
import com.example.intelligentscheduling.utils.scheduling.yichuan.Chromo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scheduling")
public class schedulingController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/{id}")
    public R schedulingById(@PathVariable int id
            , @RequestParam(value = "week",defaultValue = "0")Integer week){
        if(week == 0){
            return R.error("请输入周数");
        }
        /**
         * 先判断这一周是否已经排班
         */
        LambdaQueryWrapper<Scheduling> law = new LambdaQueryWrapper<>();
        law.eq(Scheduling::getWeek,week);
        law.eq(Scheduling::getShopId,id);
        List<Scheduling> list = schedulingService.list(law);
        if(list.size() > 0){
            return R.error("该门店该周已经拍过班了");
        }
        /**
         * 重点：实现智能排班
         */
        List<Scheduling> result = schedulingService.scheduling(id,week);



        return R.success(result);
    }

    @GetMapping("/NoRule/{id}")
    public R schedulingByIdNoRule(@PathVariable int id
            ,@RequestParam(value = "week",defaultValue = "0")int week){
        if(week == 0){
            return R.error("请输入周数");
        }
        /**
         * 重点：实现智能排班
         */
        List<Scheduling> result = schedulingService.schedulingNoRule(id,week);

        return R.success(result);
    }

    @GetMapping("/justLike/{id}")
    public R schedulingByIdJustLike(@PathVariable int id){
        /**
         * 重点：实现智能排班
         */
        List<List<String>> result = schedulingService.schedulingJustLike(id);
        return R.success(result);
    }

    @GetMapping("/{id}/select")
    public R getSchedulingByWeek(@PathVariable int id
            ,@RequestParam(value = "week",defaultValue = "0")int week){
        if(week == 0){
            return R.error("请输入周数");
        }
        List<Scheduling> schedulingByWeek = schedulingService.getSchedulingByWeek(id,week);
        if(schedulingByWeek == null || schedulingByWeek.size() == 0){
            return R.error("这周没有排序表");
        }
        return R.success(schedulingByWeek);
    }

}
