package com.example.crud.controller;

import com.example.crud.entity.CommitEvent;
import com.example.crud.service.CommitEventService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommitController {
    @Autowired
    private CommitEventService commitEventService;
    @RequestMapping("/clist")
    public String list(Model model , @RequestParam(value = "start", defaultValue = "0") int start,
                       @RequestParam(value = "size", defaultValue = "5") int size){
        //PageHelper.startPage(start,size,"id asc");
        List<CommitEvent> commitEvent= commitEventService.clist();
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        PageInfo<CommitEvent> page = new PageInfo<>(commitEvent);
        model.addAttribute("pages", page);
        return "commitlist";

    }

    @RequestMapping( "/commits")
    @ResponseBody
    public List<CommitEvent> show_commits() {
        List<CommitEvent> commitEvent= commitEventService.clist();
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        return commitEvent;
    }

    @RequestMapping( "/commits1")
    @ResponseBody
    public List<CommitEvent> commits1() {
        List<CommitEvent> commitEvent= commitEventService.findCommitByProject("Student-Grade-Accessment-System");
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        return commitEvent;
    }

    @RequestMapping( "/commits2")
    @ResponseBody
    public List<CommitEvent> commits2() {
        List<CommitEvent> commitEvent= commitEventService.findCommitByProject("DataGripTools");
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        return commitEvent;
    }

    @RequestMapping( "/commits3")
    @ResponseBody
    public List<CommitEvent> commits3() {
        List<CommitEvent> commitEvent= commitEventService.findCommitByProject("Piggy-ProjG");
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        return commitEvent;
    }

    @RequestMapping( "/commits4")
    @ResponseBody
    public List<CommitEvent> commits4() {
        List<CommitEvent> commitEvent= commitEventService.findCommitByProject("MUC2019CS-IRRASa");
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        return commitEvent;
    }

    @RequestMapping( "/commits5")
    @ResponseBody
    public List<CommitEvent> commits5() {
        List<CommitEvent> commitEvent= commitEventService.findCommitByProject("MUC2019CS-IRRASb");
        for(int i=0;i<commitEvent.size();i++){
            if(commitEvent.get(i).getPid()==1){
                commitEvent.get(i).setName("何淇");
            }
            else if(commitEvent.get(i).getPid()==2){
                commitEvent.get(i).setName("武伊雪");
            }
            else if(commitEvent.get(i).getPid()==3){
                commitEvent.get(i).setName("赵琰晴");
            }
            else if(commitEvent.get(i).getPid()==4){
                commitEvent.get(i).setName("马加辰");
            }
            else if(commitEvent.get(i).getPid()==5){
                commitEvent.get(i).setName("王君");
            }
            else if(commitEvent.get(i).getPid()==6){
                commitEvent.get(i).setName("杨皓天");
            }
            else if(commitEvent.get(i).getPid()==7){
                commitEvent.get(i).setName("陈浩楠");
            }
            else if(commitEvent.get(i).getPid()==8){
                commitEvent.get(i).setName("赵浚博");
            }
            else if(commitEvent.get(i).getPid()==9){
                commitEvent.get(i).setName("赵松青");
            }
            else if(commitEvent.get(i).getPid()==10){
                commitEvent.get(i).setName("毛泓涛");
            }
            else if(commitEvent.get(i).getPid()==11){
                commitEvent.get(i).setName("南佳霖");
            }
            else if(commitEvent.get(i).getPid()==12){
                commitEvent.get(i).setName("李金欣");
            }
            else{
                commitEvent.get(i).setName("庞基玮");
            }
        }
        return commitEvent;
    }

    @RequestMapping("/date_commits")
    public String date_commits(String project){
//        this.commits1(project);
        if(project.equals("Student-Grade-Accessment-System")){
            return "date_commits1";
        }
        else if(project.equals("DataGripTools")){
            return "date_commits2";
        }
        else if(project.equals("Piggy-ProjG")){
            return "date_commits3";
        }
        else if(project.equals("MUC2019CS-IRRASa")){
            return "date_commits4";
        }
        else {
            return "date_commits5";
        }
    }

    @RequestMapping("/date_addLines")
    public String date_addLines(String project){
        if(project.equals("Student-Grade-Accessment-System")){
            return "date_addLines1";
        }
        else if(project.equals("DataGripTools")){
            return "date_addLines2";
        }
        else if(project.equals("Piggy-ProjG")){
            return "date_addLines3";
        }
        else if(project.equals("MUC2019CS-IRRASa")){
            return "date_addLines4";
        }
        else {
            return "date_addLines5";
        }
    }

    @RequestMapping("/date_deleteLines")
    public String date_deleteLines(String project){

        if(project.equals("Student-Grade-Accessment-System")){
            return "date_deleteLines1";
        }
        else if(project.equals("DataGripTools")){
            return "date_deleteLines2";
        }
        else if(project.equals("Piggy-ProjG")){
            return "date_deleteLines3";
        }
        else if(project.equals("MUC2019CS-IRRASa")){
            return "date_deleteLines4";
        }
        else {
            return "date_deleteLines5";
        }
    }

    @RequestMapping("/date_totalchangelines")
    public String date_totalchangelines(String project){

        if(project.equals("Student-Grade-Accessment-System")){
            return "date_totalchangelines1";
        }
        else if(project.equals("DataGripTools")){
            return "date_totalchangelines2";
        }
        else if(project.equals("Piggy-ProjG")){
            return "date_totalchangelines3";
        }
        else if(project.equals("MUC2019CS-IRRASa")){
            return "date_totalchangelines4";
        }
        else {
            return "date_totalchangelines5";
        }
    }

}
