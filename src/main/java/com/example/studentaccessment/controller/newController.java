package com.example.studentaccessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class newController
{
    @RequestMapping("/index")
    public String sayHello()
    {
        return "index";
    }
}
