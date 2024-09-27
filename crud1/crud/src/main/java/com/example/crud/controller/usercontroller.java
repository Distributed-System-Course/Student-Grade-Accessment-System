package com.example.crud.controller;

import com.example.crud.entity.User;
import com.example.crud.service.userservice;
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
public class usercontroller {
    @Autowired
    private userservice userService; //注入UserService
    //跳转到登录界面
    @RequestMapping("/tologin")
    public String tologin() {

        return "login";
    }
    @RequestMapping("/head")
    public String tohead() {

        return "head";
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
    //跳转到注册界面
    @RequestMapping("/toregister")
    public String toregister() {

        return "register";
    }
    //登录逻辑实现
    @RequestMapping("/login")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        User user = userService.userLogin(username, password);
        if (user != null) {                                                  //登录成功
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session
            HttpSession session=request.getSession();//获取session并将userName存入session对象
            session.setAttribute("userName", user.getUsername());
            return "index";
        }
        return "error";
    }
    //注册逻辑实现
    @RequestMapping("/register")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("age") int age) {
        if (!password.equals(password2)) {
            return "两次密码不相同，注册失败！！";
        } else {
            int res = userService.adduser(username, password, age);
            if (res == 0) {
                return "no";
            } else {
                return "login";
            }
        }
    }
    //跳转到用户新添加的页面
    @RequestMapping("/insertPage")
    public String index(){
        return "insertPage";
    }
    //用户信息展示
    @RequestMapping("/List")
    public String userList(Model model ,@RequestParam(value = "start", defaultValue = "0") int start,
    @RequestParam(value = "size", defaultValue = "7") int size) throws Exception{
        PageHelper.startPage(start,size,"id asc");
        List<User> users = userService.userList();
        PageInfo<User> page = new PageInfo<>(users);
        model.addAttribute("pages", page);
        return "userList";
    }
    //展示柱状图
    @RequestMapping( "/show")
    @ResponseBody
    public List<User> show() {
        List<User> users=userService.userList();
        return users;

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
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("age") int age
                          ) {
            int res = userService.adduser(username, password,age);
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
        int res =  userService.delete(id);
        if (res == 0) {
            return "no";
        } else {
            return "redirect:/List";
        }

    }
    /*
     修改用户信息
     */
    @RequestMapping("/findUser")
    public String findUser2(int id, Model model) throws Exception {
        User user= userService.findUserById(id);
        model.addAttribute("user", user);
        return "updatePage";

    }

    @RequestMapping(value="/update")
    public String updateUser(User user) throws Exception {

        System.out.println("信息修改成功!用户名:"+user.getUsername()+"密码:"+user.getPassword()+"年龄："+user.getAge());
        userService.update(user);
        return "redirect:/List";
    }
    /**
     * 查询用户
     */
    @RequestMapping("/search")
    public String search(@RequestParam("username") String username, Model model) throws Exception {
       User user=userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }
    /*
    发起众筹
     */
    @RequestMapping("/new")
    public String new1(){
        return "law";

    }
    @RequestMapping("/create")
    public String applayproject(){
        return "projectadd";

    }
    @RequestMapping("/wnx")
    public String addproject(@RequestParam("pname") String pname,
                             @RequestParam("intr") String intr,
                             @RequestParam("tprice") int tprice){
       int res= userService.addproject(pname,intr,tprice);
        if (res == 0) {
            return "no";
        } else {
            return "success1";
        }


    }

}
