package com.example.crud.controller;

import com.example.crud.entity.Student;
import com.example.crud.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService; //注入StudentService

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
    //展示柱状图
    @RequestMapping( "/showbar_addlines")
    public String show5() {
        return "bar_addlines";
    }
    //展示柱状图
    @RequestMapping( "/showbar_deletelines")
    public String show6() {
        return "bar_deletelines";
    }
    //展示柱状图
    @RequestMapping( "/showbar_totalchangelines")
    public String show7() {
        return "bar_totalchangelines";
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
