package com.example.intelligentscheduling.entity;/*
 *
 * @Param
 */

import lombok.Data;

/**
 * 员工爱好表
 */
@Data
public class Preference {
    private int id;


    /**
     * 偏好
     */
    private String type;
}
