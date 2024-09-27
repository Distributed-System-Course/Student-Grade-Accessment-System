package com.example.studentaccessment.serviceImpl;

import com.example.studentaccessment.bean.UserBean;
import com.example.studentaccessment.mapper.UserMapper;
import com.example.studentaccessment.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserMapper userMapper;

    public UserBean logIn(String name, String password)
    {
        return userMapper.getInfo(name, password);
    }
}

