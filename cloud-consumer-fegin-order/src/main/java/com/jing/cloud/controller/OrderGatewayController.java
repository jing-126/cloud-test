package com.jing.cloud.controller;

import com.jing.cloud.apis.PayFeignApi;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import com.jing.cloud.utils.DateTimeZoneUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/pay/gateway")
public class OrderGatewayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/get/{id}")
    public Result<PayDTO> getById(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    @GetMapping("/info")
    public Result<String> info() {
        return payFeignApi.getGatewayInfo();
    }

    @GetMapping("/getZoned")
    public Result<String> getZoned() {
        return payFeignApi.getZonedDatetime();
    }
}
