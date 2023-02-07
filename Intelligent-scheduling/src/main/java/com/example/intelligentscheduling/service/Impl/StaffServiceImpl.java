package com.example.intelligentscheduling.service.Impl;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.mapper.StaffMapper;
import com.example.intelligentscheduling.service.StaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
}
