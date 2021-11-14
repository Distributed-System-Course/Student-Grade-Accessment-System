package com.example.crud.controller;

import com.example.crud.entity.CommitEvent;
import com.example.crud.entity.Project;
import com.example.crud.service.CommitEventService;
import com.example.crud.service.ProdjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProdjectService prodjectService;

    //产品信息的展示
    @RequestMapping("/plist")
    public String list(Model model ,@RequestParam(value = "start", defaultValue = "0") int start,
                       @RequestParam(value = "size", defaultValue = "5") int size){
        PageHelper.startPage(start,size,"pid asc");
        List<Project> project= prodjectService.pList();
        PageInfo<Project> page = new PageInfo<>(project);
        model.addAttribute("pages", page);
        return "projectlist";

    }
    @RequestMapping("/findProject")
    public String findProject(int pid, Model model) throws Exception {
        Project project= prodjectService.findProjectById(pid);
        model.addAttribute("project", project);

        return "progress";
    }
    @RequestMapping("/projectlist")
    @ResponseBody
    public List<Project> list2( ){

        List<Project> project= prodjectService.pList();


        return project;

    }
//    @RequestMapping("/ptest")
//
//    public String list3(Model model ){
//
//        List<Project> project= prodjectService.pList();
//
//        model.addAttribute("page2", project);
//        return "head";
//
//    }
}
