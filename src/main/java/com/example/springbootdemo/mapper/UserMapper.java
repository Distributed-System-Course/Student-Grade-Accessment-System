package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.UserBean;


public interface UserMapper
{
    UserBean getInfo(String name, String password);
}
