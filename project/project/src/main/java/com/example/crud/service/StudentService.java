package com.example.crud.service;

import com.example.crud.entity.Student;
import com.example.crud.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentmapper; //注入StudentMapper

    public  int addstudent(String studentname, String project,int totalCommits) {
        return studentmapper.addstudent(studentname,project,totalCommits);

    }

    /*
     * 查询所有用户
     */
    public List<Student> studentList(){
        return studentmapper.studentList();
    }

    /*
     * 增加保存用户
     */
    public void save(Student student){
        studentmapper.save(student);
    }

    /*
     * 根据id删除用户
     */
    public int delete(Integer id){
        return studentmapper.delete(id);

    }

    /*
     * 根据id查找用户
     */
    public Student findStudentById(int id){
        return studentmapper.findStudentById(id);
    }

    /*
     * 更改用户信息
     */


    public int update(Student student) {
       return  studentmapper.update(student);
    }


    public Student studentLogin(String studentname, String project) {
        Student student = studentmapper.studentlogin(studentname,project);
        return student;
    }

    public Student findStudentByStudentname(String studentname) {
        return studentmapper.findStudentByStudentName(studentname);
    }

    public int addproject(String pname, String intr, int tprice) {
      return  studentmapper.addproject(pname,intr,tprice);
    }
}
