package com.example.crud;

import com.google.gson.internal.LinkedTreeMap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.RESTCall;

import java.util.ArrayList;

@SpringBootApplication
@MapperScan("com.example.crud.mapper")
public class CrudApplication {
    void Calculate(){
        RESTCall restCall= new RESTCall();
        String url="https://api.github.com/repos/Distributed-System-Course/Student-Grade-Accessment-System/events";
        ArrayList<LinkedTreeMap<String,Object>> table=restCall.returnTable(url,null);

    }
    public static void main(String[] args) {

        SpringApplication.run(CrudApplication.class, args);
    }

}
