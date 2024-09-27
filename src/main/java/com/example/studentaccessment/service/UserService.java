package com.example.studentaccessment.service;

import com.example.studentaccessment.bean.UserBean;

public interface UserService
{
    UserBean logIn(String name, String password);
}
