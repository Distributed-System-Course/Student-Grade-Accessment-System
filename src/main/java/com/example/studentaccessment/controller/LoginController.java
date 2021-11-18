package com.example.studentaccessment.controller;

import com.example.studentaccessment.bean.UserBean;
import com.example.studentaccessment.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testng.annotations.Test;

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
}
