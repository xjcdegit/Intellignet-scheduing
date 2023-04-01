package com.example.intelligentscheduling.service.Impl;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.intelligentscheduling.entity.Scheduling;
import com.example.intelligentscheduling.entity.Shop;
import com.example.intelligentscheduling.entity.Staff;
import com.example.intelligentscheduling.mapper.SchedulingMapper;
import com.example.intelligentscheduling.service.SchedulingService;
import com.example.intelligentscheduling.utils.scheduling.Stuff;
import com.example.intelligentscheduling.utils.scheduling.main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SchedulingServiceImpl extends ServiceImpl<SchedulingMapper, Scheduling> implements SchedulingService {
    @Autowired
    private ShopServiceImpl shopService;
    @Autowired
    private PreferenceServiceImpl preferenceService;
    @Autowired
    private StaffServiceImpl staffService;
    //线程池，分配十个线程
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);
    /**
     * 实现排班
     * @param id
     * @return
     */
    @Override
    public List<Scheduling> scheduling(int id,int week) {
        //1.获取该门店所有的员工
        List<Staff> staffList = shopService.selectAllStaffById(id);
        //2.获取该门店的信息
        Shop shop = shopService.selectById(id);

        //转为算法中需要的格式
        List<Stuff> list = new ArrayList<>();
        //存储员工信息
        for (Staff staff:staffList){
            list.add(new Stuff(staff.getName(),staff.getPost(),staff.getTel(),staff.getEmail(),String.valueOf(staff.getShopId())
                    ,preferenceService.selectById(staff.getId()).getType() ));
        }
        //3.实现排班
        List<List<String>> scheduling = main.scheduling(list, shop);

        //4.将得到的结果转存为Scheduling数组的格式进行返回
        List<Scheduling> result = new ArrayList<>();
        int index = 0;
        int day = 1;
        for (List<String> list1:scheduling){
            for (String str:list1){
                result.add(new Scheduling(id,staffList.get(index).getId(),day ,str,week));
                index++;
            }
            index = 0;
            day++;
        }


        //多线程：进行上传数据库
        CACHE_REBUILD_EXECUTOR.submit(() -> {
            /*List<Scheduling> schedulingList = new ArrayList<>();
            int day = 1;
            for(Map<Integer,String> map:result){
                for (Staff staff:StaffList){
                    schedulingList.add(new Scheduling(id, staff.getId(), day, map.get(staff.getId() - 1), 1));
                }
                day++;
            }*/
            //5.批量插入
            this.saveBatch(result);
        });

        //6.返回排班结果
        return result;
    }

    /**
     * 实现不实现偏好的排班
     * @param id
     * @return
     */
    @Override
    public List<Scheduling> schedulingNoRule(int id,int week) {
        //1.获取该门店所有的员工
        List<Staff> staffList = shopService.selectAllStaffById(id);
        //2.获取该门店的信息
        Shop shop = shopService.selectById(id);

        //转为算法中需要的格式
        List<Stuff> list = new ArrayList<>();
        //存储员工信息
        for (Staff staff:staffList){
            list.add(new Stuff(staff.getName(),staff.getPost(),staff.getTel(),staff.getEmail(),String.valueOf(staff.getShopId())
                    , "无偏好，正常工作"));
        }
        //3.实现排班
        List<List<String>> scheduling = main.scheduling(list, shop);

        //4.将得到的结果转存为Scheduling数组的格式进行返回
        List<Scheduling> result = new ArrayList<>();
        int index = 0;
        int day = 1;
        for (List<String> list1:scheduling){
            for (String str:list1){
                result.add(new Scheduling(id,staffList.get(index).getId(),day ,str,week));
                index++;
            }
            index = 0;
            day++;
        }


        //多线程：进行上传数据库
        CACHE_REBUILD_EXECUTOR.submit(() -> {
            /*List<Scheduling> schedulingList = new ArrayList<>();
            int day = 1;
            for(Map<Integer,String> map:result){
                for (Staff staff:StaffList){
                    schedulingList.add(new Scheduling(id, staff.getId(), day, map.get(staff.getId() - 1), 1));
                }
                day++;
            }*/
            //5.批量插入
            this.saveBatch(result);
        });

        //6.返回排班结果
        return result;
    }


    /**
     * 只根据喜欢的进行排班
     * @param id
     * @return
     */
    @Override
    public List<List<String>> schedulingJustLike(int id) {
        //获取该门店所有的员工
        List<Staff> staffList = shopService.selectAllStaffById(id);
        //获取该门店的信息
        Shop shop = shopService.selectById(id);
        List<Stuff> list = new ArrayList<>();
        //public Stuff(String name, String pos, String tel, String mail, String stores, String loves)
        for (Staff staff:staffList){
            list.add(new Stuff(staff.getName(),staff.getPost(),staff.getTel(),staff.getEmail(),String.valueOf(staff.getShopId())
                    ,"无偏好，正常工作" ));
        }
        List<List<String>> scheduling = main.scheduling(list, shop);
        return scheduling;
    }

    /**
     * 根据周数获取排班表
     * @param week
     * @return
     */
    @Override
    public List<Scheduling> getSchedulingByWeek(int id,int week) {
        LambdaQueryWrapper<Scheduling> law = new LambdaQueryWrapper<>();
        law.eq(Scheduling::getWeek,week);
        law.eq(Scheduling::getShopId,id);
        List<Scheduling> list = list(law);
        return list;
    }
}
