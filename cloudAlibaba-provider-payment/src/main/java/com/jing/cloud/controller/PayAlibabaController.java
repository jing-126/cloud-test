package com.jing.cloud.controller;

import com.jing.cloud.apis.PayFeignApi;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class PayAlibabaController {
    @Value("${server.port}")
    private String serverPort;
//
//    @Resource
//    private PayFeignApi payService;

//    @GetMapping("/pay/{id}")
//    public Result<PayDTO> getPayInfo(@PathVariable("id") Integer id) {
//        return payService.getById(id);
//    }

    @GetMapping("/pay/{id}")
    public Result<String> getPayInfo(@PathVariable("id") Integer id) {
        return Result.success("nacos registry, serverPort:" + serverPort + "id:" + id);
    }
}
