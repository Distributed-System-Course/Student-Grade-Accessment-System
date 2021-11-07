package com.example.crud.service;

import com.example.crud.entity.User;
import com.example.crud.mapper.usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userservice {
    @Autowired
    private usermapper usermapper; //注入UserMapper

//    public  int adduser(String username, String password,int age) {
//        return usermapper.adduser(username,password,age);
//
//    }

    /*
     * 查询所有用户
     */
    public List<User> userList(){
        return usermapper.userList();
    }

    /*
     * 增加保存用户
     */
//    public void save(User user){
//        usermapper.save(user);
//    }

    /*
     * 根据id删除用户
     */
//    public int delete(Integer id){
//        return usermapper.delete(id);
//
//    }

    /*
     * 根据id查找用户
     */
//    public User findUserById(int id){
//        return usermapper.findUserById(id);
//    }

    /*
     * 更改用户信息
     */


//    public int update(User user) {
//       return  usermapper.update(user);
//    }
//
//
//    public User userLogin(String username, String password) {
//        User user = usermapper.userlogin(username,password);
//        return user;
//    }

    public User findUserByUsername(String username) {
        return usermapper.findUserByUserName(username);
    }

//    public int addproject(String pname, String intr, int tprice) {
//      return  usermapper.addproject(pname,intr,tprice);
//    }
}
