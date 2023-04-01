package com.example.intelligentscheduling.service;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.intelligentscheduling.entity.Preference;

public interface PreferenceService extends IService<Preference> {
    /**
     * 根据用户id获取偏好
     */
    public Preference selectById(int id);
}
