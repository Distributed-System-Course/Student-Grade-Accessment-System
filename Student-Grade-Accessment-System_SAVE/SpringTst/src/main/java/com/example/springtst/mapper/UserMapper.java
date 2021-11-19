package com.example.springtst.mapper;

import com.example.springtst.bean.UserBean;

public interface UserMapper {
    UserBean getInfo(String name,String password);
}
