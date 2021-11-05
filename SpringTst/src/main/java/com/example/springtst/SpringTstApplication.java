package com.example.springtst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springtst.mapper")
public class SpringTstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTstApplication.class, args);
    }

}
