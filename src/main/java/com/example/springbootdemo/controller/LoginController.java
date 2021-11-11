package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.UserBean;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class LoginController
{
    @Resource
    UserService userService;

    @RequestMapping("/loginView")
    public String show()
    {
        return "loginView";
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String loginMethod(String name, String password)
    {
        UserBean userBean = userService.logIn(name, password);
        if(userBean != null)
            return "success";
        else
            return "error";
    }

/*
    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String loginGetMethod(String name, String password)
    {
        UserBean userBean = userService.logIn(name, password);
        if(userBean != null)
            return "success";
        else
            return "error";
    }*/
}
