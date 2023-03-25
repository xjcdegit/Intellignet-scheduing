package com.example.intelligentscheduling.controller;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.Preference;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.service.PreferenceService;
import com.example.intelligentscheduling.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private PreferenceService preferenceService;

    /**
     * 获取员工喜好
     */
    @GetMapping
    public R getPreferenceById(@PathVariable int id){
        Preference preference = preferenceService.getById(id);
        return R.success(preference);
    }

    /**
     * 修改员工喜好
     * @param preference
     * @return
     */
    @PostMapping("/preference")
    public R preference(@RequestBody Preference preference){
        boolean loop = preferenceService.updateById(preference);
        return R.success("修改成功");
    }

    /**
     * 获取所有员工
     */
    @GetMapping("/all")
    public R getStaffAll(){
        List<Staff> list = staffService.list();
        return R.success(list);
    }

}
