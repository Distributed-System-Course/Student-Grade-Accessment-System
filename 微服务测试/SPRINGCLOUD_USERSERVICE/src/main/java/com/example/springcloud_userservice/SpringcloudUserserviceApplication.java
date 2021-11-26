package com.example.springcloud_userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//启用eureka的客户端
@EnableEurekaClient
public class SpringcloudUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudUserserviceApplication.class, args);
    }

}
