package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//启用eureka的客户端
@EnableEurekaClient
@EnableDiscoveryClient
public class GithubApi {

    public static void main(String[] args) {
        SpringApplication.run(GithubApi.class, args);
    }
}
