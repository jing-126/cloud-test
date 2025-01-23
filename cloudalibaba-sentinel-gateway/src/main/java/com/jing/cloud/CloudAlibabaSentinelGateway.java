package com.jing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaSentinelGateway {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(CloudAlibabaSentinelGateway.class, args);
    }
}