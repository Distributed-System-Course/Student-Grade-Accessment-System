package com.example.studentaccessment.mapper;

import com.example.studentaccessment.bean.GroupBean;
import com.example.studentaccessment.bean.UserBean;

//创建DAO层访问数据库接口UserMapper
//UserMapper.xml中使用该函数
public interface UserMapper
{
    UserBean getInfo(String name, String password);
}
