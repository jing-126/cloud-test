package com.jing.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope // 动态刷新和全局广播通知
public class NacosConfigClientController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/info")
    public String info() {
        return info;
    }
}
