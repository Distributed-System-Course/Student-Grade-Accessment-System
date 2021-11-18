package com.example.studentaccessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class choiceController
{
    @RequestMapping("/choice")
    public String show(){ return "choice";}

    @RequestMapping(value = "/getInfor", method = RequestMethod.POST)
    public String getInforMethod(String num)
    {
        if(num.equals("1"))
            return "table";
        else
            return "error";
    }
}
