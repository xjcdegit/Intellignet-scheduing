package com.example.intelligentscheduling.service.Impl;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.intelligentscheduling.entity.Scheduling;
import com.example.intelligentscheduling.mapper.SchedulingMapper;
import com.example.intelligentscheduling.service.SchedulingService;
import org.springframework.stereotype.Service;

@Service
public class SchedulingServiceImpl extends ServiceImpl<SchedulingMapper, Scheduling> implements SchedulingService {
}
