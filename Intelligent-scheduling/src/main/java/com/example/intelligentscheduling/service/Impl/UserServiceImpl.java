package com.example.intelligentscheduling.service.Impl;/*
 *
 * @Param
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.intelligentscheduling.entity.User;
import com.example.intelligentscheduling.mapper.UserMapper;
import com.example.intelligentscheduling.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
