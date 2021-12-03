package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//启用eureka的客户端
@EnableEurekaClient
@EnableDiscoveryClient
public class SpringcloudUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudUserserviceApplication.class, args);
    }

}
