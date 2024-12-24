package com.jing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 注册发现
public class CloudGateway {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateway.class, args);
    }
}