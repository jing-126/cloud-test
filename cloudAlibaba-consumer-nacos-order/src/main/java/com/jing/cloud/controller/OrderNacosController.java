package com.jing.cloud.controller;

import com.jing.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${server-url.nacos-user-service}")
    private String providerService;

    @GetMapping("/pay/nacos/{id}")
    public Result<String> getPayInfo(@PathVariable Integer id) {
        return restTemplate.getForObject(String.format("%s/nacos/pay/%s", providerService, id), Result.class);
    }
}
