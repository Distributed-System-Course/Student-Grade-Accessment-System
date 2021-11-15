package com.example.crud.controller;

import com.example.crud.service.CommitEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommitController {
    @Autowired
    private CommitEventService commitEventService;

    @RequestMapping("/date_commits")
    public String date_commits(String project, Model model) throws Exception{
        //CommitEvent commit=commitService.findCommitByProject(project);
        //model.addAttribute("commit",commit);

        return "date_commits";
    }
}
