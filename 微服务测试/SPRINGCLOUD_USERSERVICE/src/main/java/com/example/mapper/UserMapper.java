package com.example.mapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    //@Select("select * from user where user=#{userName} and authentication_string=password(#{password})")
    @Select("select * from user where username=#{userName} and password=#{password}")
    public List<User> queryUser(@Param("userName") String userName, @Param("password")String password);
}
