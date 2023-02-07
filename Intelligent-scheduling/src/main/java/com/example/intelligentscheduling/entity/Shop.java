package com.example.intelligentscheduling.entity;/*
 *
 * @Param
 */

import lombok.Data;

/**
 * 门店表
 */
@Data
public class Shop {
    private int id;

    private String name;

    private String address;

    private double size;
}
