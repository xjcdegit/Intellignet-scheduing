package com.example.intelligentscheduling.entity;/*
 *
 * @Param
 */

import lombok.Data;

/**
 * 排班表
 */
@Data
public class Scheduling {
    //门店id
    private int shopId;

    //员工id
    private int staffId;

    //工作日期（星期几）
    private int day;

    //工作时间
    private String time;

    //周数
    private int week;

    public Scheduling(int shopId, int staffId, int day, String time, int week) {
        this.shopId = shopId;
        this.staffId = staffId;
        this.day = day;
        this.time = time;
        this.week = week;
    }
}
