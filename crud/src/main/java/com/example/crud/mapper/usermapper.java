package com.example.crud.mapper;

import com.example.crud.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository  //该注解是要与启动类中的@mapperscan配套使用
public interface usermapper {
    /*
     * 查询所有用户
     */
    List<User> userList();

    /*
     * 增加保存用户
     */
//    void save(User user);
//
//    /*
//     * 根据id删除用户
//     */
//    int delete(Integer id);

    /*
     * 根据id查找用户
     */
    User findUserById(int id);

    /*
     * 更改用户信息
     */
//    int update(User user);
//
//    int adduser(@Param("username") String username, @Param("password") String password,@Param("age") int age);
//
//
//    User userlogin(@Param("username") String username, @Param("password") String password);

    User findUserByUserName(@Param("name") String name);
//
//    int addproject(@Param("pname")String pname,@Param("intr")String intr, @Param("tprice")int tprice);
}
