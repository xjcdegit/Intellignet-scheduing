package com.example.intelligentscheduling.entity;/*
 *
 * @Param
 */

import lombok.Data;

/**
 * 员工表
 */
@Data
public class Staff {
    private int id;

    //门店id
    private int shopId;

    //名字
    private String name;
}
