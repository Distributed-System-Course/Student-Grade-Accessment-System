package com.example.springtst.servicelmpl;

import com.example.springtst.bean.UserBean;
import com.example.springtst.mapper.UserMapper;
import com.example.springtst.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    //将DAO注入Sever层
    @Resource
    private UserMapper userMapper;
    @Override
    public UserBean logIn(String name, String password) {
        return userMapper.getInfo(name,password);
    }
}
