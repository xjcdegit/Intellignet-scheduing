package com.example.intelligentscheduling.service.Impl;/*
 *
 * @Param
 */


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.intelligentscheduling.entity.Preference;
import com.example.intelligentscheduling.mapper.PreferenceMapper;
import com.example.intelligentscheduling.service.PreferenceService;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper,Preference> implements PreferenceService{
    /**
     * 根据用户id获取偏好
     * @param id
     * @return
     */
    @Override
    public Preference selectById(int id) {
        return getById(id);
    }
}
