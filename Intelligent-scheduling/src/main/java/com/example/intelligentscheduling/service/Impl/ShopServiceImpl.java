package com.example.intelligentscheduling.service.Impl;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.intelligentscheduling.common.R;
import com.example.intelligentscheduling.entity.Shop;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.mapper.ShopMapper;
import com.example.intelligentscheduling.service.ShopService;
import com.example.intelligentscheduling.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {
    @Autowired
    private StaffService staffService;
    /**
     * 根据店面id查找所有员工
     * @return
     */
    @Override
    public List<Staff> selectAllStaffById(int id) {
        LambdaQueryWrapper<Staff> law = new LambdaQueryWrapper<>();
        law.eq(Staff::getShopId,id);

        List<Staff> StaffList = staffService.list(law);
        return StaffList;
    }

    /**
     * 添加员工
     * @param staff
     * @return
     */
    @Override
    public R insert(Staff staff) {
        boolean loop = staffService.save(staff);
        if(loop){
            return R.success(staff);
        }
        return R.error("添加失败");
    }

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    @Override
    public boolean deleteByStaffId(int id) {
        return staffService.removeById(id);
    }

    /**
     * 根据门店号获取门店信息
     * @param id
     * @return
     */
    @Override
    public Shop selectById(int id) {
        Shop shop = this.getById(id);
        return shop;
    }
}
