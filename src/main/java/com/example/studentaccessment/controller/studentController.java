package com.example.studentaccessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class studentController {
    @RequestMapping("/student")
    public String student(Model model){
        model.addAttribute("name","Jack");
        model.addAttribute("age",20);
        model.addAttribute("info","good");
        return "student";
    }
}
