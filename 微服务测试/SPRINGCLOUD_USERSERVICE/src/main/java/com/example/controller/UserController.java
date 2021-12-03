package com.example.controller;

import com.example.entity.Result;
import com.example.entity.User;
import com.example.mapper.UserMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @PostMapping("/validateUser")
    public Result validateUser(@RequestBody User user){
        List<User> maps = userMapper.queryUser(user.getUserName(), user.getPassword());
        // 将获得的数据经过逻辑验证返回给界面微服务的控制层
        Result re = new Result();
        if(maps.size()>0){
            re.setCode(1);
            re.setMsg("请输入正确的用户名和密码");
        }
        return re;
    }
}
