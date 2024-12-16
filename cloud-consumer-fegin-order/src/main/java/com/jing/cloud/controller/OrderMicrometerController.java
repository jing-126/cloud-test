package com.jing.cloud.controller;

import com.jing.cloud.apis.PayFeignApi;
import com.jing.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class OrderMicrometerController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/micrometer/{id}")
    public Result<String> myMicrometer(@PathVariable("id") String id) {
        return payFeignApi.payMicrometer(id);
    }
}
