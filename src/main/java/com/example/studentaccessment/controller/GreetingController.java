package com.example.studentaccessment.controller;

import com.example.studentaccessment.Greeting;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

//    @RequestMapping("/student")
//    public String student(Model model){
//        model.addAttribute("name","Jack");
//        model.addAttribute("age",20);
//        model.addAttribute("info","good");
//        return "student";
//    }
}