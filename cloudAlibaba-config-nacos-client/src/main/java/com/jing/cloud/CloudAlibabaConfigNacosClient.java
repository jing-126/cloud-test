package com.jing.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
//@RefreshScope // 动态刷新和全局广播通知
public class CloudAlibabaConfigNacosClient {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaConfigNacosClient.class, args);
    }
}