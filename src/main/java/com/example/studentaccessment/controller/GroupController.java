package com.example.studentaccessment.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GroupController {
    @RequestMapping("/guidance")
    public String show(){ return "guidance";}

    @RequestMapping(value = "/student_one", method = RequestMethod.PUT)
    public String transform() { return "student_one"; }
}
