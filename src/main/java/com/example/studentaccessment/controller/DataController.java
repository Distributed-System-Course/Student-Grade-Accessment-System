package com.example.studentaccessment.controller;

import com.example.studentaccessment.bean.DataBean;
import com.example.studentaccessment.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class DataController {

    @Resource
    DataService dataService;

    @RequestMapping("/show")
    @ResponseBody
    public List<DataBean> findByName(Model model){
        List<DataBean> data= dataService.dataList();
        System.err.println(data.toString());
        return data;
    }

//    展示柱状图
    @RequestMapping("/showbar")
    public String show2(){
        return "bar";
    }

//    展示饼图
    @RequestMapping("/showpie")
    public String show3(){
        return "pie";
    }

//    展示折线图
    @RequestMapping("/showline")
    public String show4(){
        return "line";
    }
}
