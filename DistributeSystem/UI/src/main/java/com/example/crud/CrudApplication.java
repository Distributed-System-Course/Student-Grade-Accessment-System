package com.example.crud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.example.crud.mapper")
public class CrudApplication {
    @Bean
    // 支持负载均衡功能
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        int port = 8080;
        String portPrefix = "--server.port=";
        for (String arg : args) {
            if (arg.startsWith(portPrefix)) {
                port = Integer.parseInt(arg.substring(portPrefix.length()));
            }
        }
        SpringApplication.run(CrudApplication.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:" + port+"/tologin");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
