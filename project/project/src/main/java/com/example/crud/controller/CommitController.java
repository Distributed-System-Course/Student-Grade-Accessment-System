package com.example.crud.controller;

import com.example.crud.entity.Commit;
import com.example.crud.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommitController {
    @Autowired
    private CommitService commitService;

    @RequestMapping("/date_commits")
    public String date_commits(String project, Model model) throws Exception{
        Commit commit=commitService.findCommitByProject(project);
        model.addAttribute("commit",commit);

        return "date_commits";
    }
}
