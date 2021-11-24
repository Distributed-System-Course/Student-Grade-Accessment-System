package com.example.springtst.service;

import com.example.springtst.bean.UserBean;

public interface UserService {
    UserBean logIn(String name,String password);
}
