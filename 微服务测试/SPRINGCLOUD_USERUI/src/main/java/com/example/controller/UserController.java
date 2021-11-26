package com.example.controller;

import com.example.entity.Result;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/rlogin")
    public String rlogin(User user){
        // 需要调用userservice这个微服务,注册中心里面服务名是大写
        // validateUser：指要转跳的微服务的控制层的路径
        String url = "http://USERSERVICE/validateUser";
        // user：将user获取的数据传到微服务
        // Result.class:指微服务返回的类型
        ResponseEntity<Result> objectResponseEntity = restTemplate.postForEntity(url,user ,Result.class);
        Result body = objectResponseEntity.getBody();
        // 当重微服务得到的Result对象的数据中code属性是1时表示，登录成功
        if(body.getCode()==1){
            return "redirect:/suc.html";
        }else{
            return "redirect:/login.html";
        }
    }
}
