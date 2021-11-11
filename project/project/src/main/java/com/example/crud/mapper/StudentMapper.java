package com.example.crud.mapper;

import com.example.crud.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository  //该注解是要与启动类中的@mapperscan配套使用
public interface StudentMapper {
    /*
     * 查询所有用户
     */
    List<Student> studentList();

    /*
     * 增加保存用户
     */
    void save(Student student);

    /*
     * 根据id删除用户
     */
    int delete(Integer id);

    /*
     * 根据id查找用户
     */
    Student findStudentById(int id);

    /*
     * 更改用户信息
     */
    int update(Student student);

    int addstudent(@Param("studentname") String studentname, @Param("project") String project,@Param("totalCommits") int totalCommits);


    Student studentlogin(@Param("studentname") String studentname, @Param("project") String project);

    Student findStudentByStudentName(@Param("studentname") String studentname);

    int addproject(@Param("pname")String pname,@Param("intr")String intr, @Param("tprice")int tprice);
}
