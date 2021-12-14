package com.example.crud.controller;

import com.example.crud.entity.ApiRequest;
import com.example.crud.entity.ApiResult;
import com.example.crud.entity.CommitEvent;
import com.example.crud.entity.Student;
import com.example.crud.service.CommitEventService;
import com.example.crud.service.StudentService;
import com.example.crud.utils.CpachaUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Controller
public class StudentController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private StudentService studentService; //注入StudentService
    @Autowired//它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
    private CommitEventService commitEventService;
    boolean flag=false;
    void commitEventUpdate(){
        ThreadPoolExecutor poolExecutor =(ThreadPoolExecutor) Executors.newCachedThreadPool();
        String[] names ={
                "Student-Grade-Accessment-System",
                "MUC2019CS-IRRASa",
                "MUC2019CS-IRRASb",
                "Piggy-ProjG",
                "DataGripTools",
                "Team-System"
        };

        List<FutureTask<ArrayList<LinkedTreeMap<String,Object>>>> results= new ArrayList<>();
        ArrayList<LinkedTreeMap<String,Object>> res = new ArrayList<>();
        for(String projectName:names){
            String prefix="https://api.github.com/repos/Distributed-System-Course/";
            String suffix="/events";
            String url=prefix+projectName+suffix;

            //com.RESTCall restCall =new RESTCall();
            String token="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";//personal
            FutureTask<ArrayList<LinkedTreeMap<String,Object>>>
                    task = new FutureTask<ArrayList<LinkedTreeMap<String,Object>>>(()->{
                String serviceUrl="http://USERSERVICE/getStudentInfo";// 在userservice里找getStudentInfo
                ApiRequest apiRequest=new ApiRequest();
                apiRequest.setApiUrl(url);
                ResponseEntity<ApiResult> objectResponseEntity=
                        restTemplate.postForEntity(serviceUrl,apiRequest,
                                ApiResult.class);
                ArrayList<LinkedTreeMap<String,Object>> table= objectResponseEntity.getBody().getTable();
                return table;
            });
            results.add(task);
            poolExecutor.execute(task);
        }
        for(FutureTask<ArrayList<LinkedTreeMap<String,Object>>> result:results) {
            try {
                res.addAll(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (LinkedTreeMap<String,Object> row:res){
            if(row.keySet().size()==6){
                CommitEvent commitEvent = new CommitEvent();
                if (row.get("author").equals("mucerhq"))
                    commitEvent.setPid(1);
                else if (row.get("author").equals("LILY123-lang"))
                    commitEvent.setPid(2);
                else if (row.get("author").equals("zhao-yanqing"))
                    commitEvent.setPid(3);
                else if (row.get("author").equals("MichaelJackchen"))
                    commitEvent.setPid(4);
                else if (row.get("author").equals("2994856495"))
                    commitEvent.setPid(5);
                else if (row.get("author").equals("MuBai-Argo"))
                    commitEvent.setPid(6);
                else if (row.get("author").equals("chn0213"))
                    commitEvent.setPid(7);
                else if (row.get("author").equals("BestJob2000"))
                    commitEvent.setPid(8);
                else if (row.get("author").equals("LibertyChaser"))
                    commitEvent.setPid(9);
                else if (row.get("author").equals("tsagaanbar"))
                    commitEvent.setPid(10);
                else if (row.get("author").equals("Nan-J"))
                    commitEvent.setPid(11);
                else if (row.get("author").equals("TxjbWwdh"))
                    commitEvent.setPid(12);
                else if (row.get("author").equals("pangjiwei"))
                    commitEvent.setPid(13);
                commitEvent.setCommitDate((String) row.get("date"));
                String project=(String) row.get("project");
                commitEvent.setProject(project.split("/")[1]);
                commitEvent.setAddLines((int) row.get("addLines"));
                commitEvent.setDeleteLines((int) row.get("deleteLines"));
                commitEvent.setChangeLines((int) row.get("totalLines"));
                commitEventService.save(commitEvent);
            }
        }

    }

    //登录界面
    @RequestMapping("/tologin")
    public String tologin() {

        return "login";
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

    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
                               @RequestParam(value="w",defaultValue="110",required=false) Integer w,
                               @RequestParam(value="h",defaultValue="39",required=false) Integer h){
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        //request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        flag=true;
        //
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
