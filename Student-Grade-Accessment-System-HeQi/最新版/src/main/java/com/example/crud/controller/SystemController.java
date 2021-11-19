package com.example.crud.controller;

import com.example.crud.entity.Admin;
import com.example.crud.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/login_1")
    public String login(){
        return "login_1";
    }


    @PostMapping("/login_1")
    @ResponseBody
    public AjaxResult submitlogin(String username, String password){
        System.out.println("159159159");
        AjaxResult ajaxResult = new AjaxResult();
        if(StringUtils.isEmpty(username)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(password)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }


        //数据库校验

        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setUsername(username);
        System.out.println("159159159");
        Admin ad = adminService.findByAdmin(admin);
        System.out.println("66666");
        if(StringUtils.isEmpty(ad)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("用户名或密码错误");
            return ajaxResult;
        }
        ajaxResult.setSuccess(true);
        //session.setAttribute(Const.ADMIN,ad);
        System.out.println("7777");


        ajaxResult.setMessage("登陆成功");
        return ajaxResult;
    }




}
