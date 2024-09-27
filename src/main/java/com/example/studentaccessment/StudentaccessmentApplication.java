package com.example.studentaccessment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.studentaccessment.mapper")
public class StudentaccessmentApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(StudentaccessmentApplication.class, args);
    }

}
