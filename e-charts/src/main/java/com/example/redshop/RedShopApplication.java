package com.example.redshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.redshop.mapper")
@SpringBootApplication()
public class RedShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedShopApplication.class, args);
    }

}
