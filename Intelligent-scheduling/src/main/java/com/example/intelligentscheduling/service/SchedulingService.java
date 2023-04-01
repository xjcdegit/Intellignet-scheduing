package com.example.intelligentscheduling.service;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.intelligentscheduling.entity.Scheduling;
import com.example.intelligentscheduling.entity.Staff;

import java.util.List;
import java.util.Map;

public interface SchedulingService extends IService<Scheduling> {
//    List<Map<Integer, String>> scheduling(int id);

    List<Scheduling> scheduling(int id,int week);

    List<Scheduling> schedulingNoRule(int id,int week);

    List<List<String>> schedulingJustLike(int id);

    List<Scheduling> getSchedulingByWeek(int id,int week);
}
