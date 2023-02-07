package com.example.intelligentscheduling.service;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.Shop;
import com.example.intelligentscheduling.entity.Staff;

import java.util.List;

public interface ShopService extends IService<Shop> {
    List<Staff> selectAllStaffById(int id);

    R insert(Staff staff);
}
