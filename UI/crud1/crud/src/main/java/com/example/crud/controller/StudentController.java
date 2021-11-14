package com.example.crud.controller;

import com.RESTCall;
import com.example.crud.entity.CommitEvent;
import com.example.crud.entity.Student;
import com.example.crud.service.CommitEventService;
import com.example.crud.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService; //注入StudentService
    @Autowired//它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
    private CommitEventService commitEventService;
    boolean flag=false;
    void commitEventUpdate(){
        com.RESTCall restCall =new RESTCall();
        String url="https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events";
        String token="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";//personal
        ArrayList<LinkedTreeMap<String,Object>> res= restCall.returnTable(url,token);
        for (LinkedTreeMap<String,Object> row:res){
            if(row.keySet().size()==6){
                CommitEvent commitEvent = new CommitEvent();

                if (row.get("author").equals("mucerhq"))
                    commitEvent.setPid(1);
                else if (row.get("author").equals("zhao-yanqing"))
                    commitEvent.setPid(3);
                else if (row.get("author").equals("LILY123-lang"))
                    commitEvent.setPid(2);
                String temp= (String) row.get("date");
                commitEvent.setCommitDate(temp);
                Integer t= (Integer) row.get("addLines")- (Integer) row.get("deleteLines");
                commitEvent.setTotalCommit(t.intValue());
                commitEventService.save(commitEvent);
            }
        }
    }
    //跳转到水球图页面
    @RequestMapping("/progress")
    public String progress() {

        return "progress";
    }

    @RequestMapping("/m")
    public String showe() {

        return "success";
    }

    //登录逻辑实现
    @RequestMapping("/login")
    public String studentLogin(@RequestParam("studentname") String studentname, @RequestParam("project") String project, HttpServletRequest request) {
        Student student = studentService.studentLogin(studentname, project);
        if (student != null) {                                                  //登录成功
            request.getSession().setAttribute("session_student", student);     //将用户信息放入session
            HttpSession session=request.getSession();//获取session并将studentName存入session对象
            session.setAttribute("studentName", student.getStudentname());
            return "index";
        }
        return "error";
    }

    //学生信息展示
    @RequestMapping("/List")
    public String studentList(Model model ,@RequestParam(value = "start", defaultValue = "0") int start,
    @RequestParam(value = "size", defaultValue = "7") int size) throws Exception{
        PageHelper.startPage(start,size,"id asc");
        List<Student> students = studentService.studentList();
        PageInfo<Student> page = new PageInfo<>(students);
        model.addAttribute("pages", page);
        return "studentList";
    }
    //展示柱状图
    @RequestMapping( "/show")
    @ResponseBody
    public List<Student> show() {
        if (!flag){
            commitEventUpdate();
            commitEventService.updateStudent();
        }
        //更新list
        flag=true;

        List<Student> students=studentService.studentList();
        return students;

    }
    //展示柱状图
    @RequestMapping( "/showbar")
    public String show2() {
        return "bar";
    }
    //展示饼图
    @RequestMapping( "/showpie")
    public String show3() {
        return "pie";
    }
    //展示折线图
    @RequestMapping( "/showline")
    public String show4() {
        return "line";
    }
    //用户添加操作
    @RequestMapping("/insert")
    public String addStudent(@RequestParam("studentname") String studentname,
                          @RequestParam("project") String project,
                          @RequestParam("totalCommits") int totalCommits
                          ) {
            int res = studentService.addstudent(studentname, project,totalCommits);
            if (res == 0) {
                return "no";
            } else {
                return "redirect:List";
            }
    }
    /*
    * 根据id删除用户
    * */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        int res =  studentService.delete(id);
        if (res == 0) {
            return "no";
        } else {
            return "redirect:/List";
        }

    }

    @RequestMapping(value="/update")
    public String updateStudent(Student student) throws Exception {

        System.out.println("信息修改成功!用户名:"+student.getStudentname()+"密码:"+student.getProject()+"年龄："+student.getTotalCommits());
        studentService.update(student);
        return "redirect:/List";
    }
}
