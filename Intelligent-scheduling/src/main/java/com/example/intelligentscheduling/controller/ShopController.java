package com.example.intelligentscheduling.controller;/*
 *
 * @Param
 */

import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * 根据店面id查询员工
     * @return
     */
    @GetMapping("/staff/{id}")
    public R selectAllStaffById(@PathVariable int id){
        List<Staff> staff = shopService.selectAllStaffById(id);
        if(staff.isEmpty()){
            return R.error("该店面还没有店员");
        }else{
            return R.success(staff);
        }
    }

    /**
     * 添加员工
     */
    @PostMapping("/insert")
    public R insert(@RequestBody Staff staff){
        return shopService.insert(staff);
    }

    /**
     * 删除员工
     */
    @GetMapping("/delete")
    public R deleteByStaffId(@PathVariable int id){
        if(shopService.deleteByStaffId(id)){
            return R.success();
        }else{
            return R.error("删除失败");
        }
    }
}
