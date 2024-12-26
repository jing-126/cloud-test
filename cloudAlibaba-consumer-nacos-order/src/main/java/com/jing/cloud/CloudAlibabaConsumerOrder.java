package com.jing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaConsumerOrder {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(CloudAlibabaConsumerOrder.class, args);
    }
}