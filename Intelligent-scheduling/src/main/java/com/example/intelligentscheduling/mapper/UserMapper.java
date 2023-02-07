package com.example.intelligentscheduling.mapper;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.intelligentscheduling.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
