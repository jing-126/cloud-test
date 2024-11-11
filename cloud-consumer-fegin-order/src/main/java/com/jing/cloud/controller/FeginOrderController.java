package com.jing.cloud.controller;

import com.jing.cloud.apis.PayFeignApi;
import com.jing.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fegin")
public class FeginOrderController {
    @Resource
    private PayFeignApi api;

    @GetMapping("/consul")
    public Result getConsulInfo() {
        return api.getInfoByConsul();
    }

    @GetMapping("/pay/get/{id}")
    Result getPay(@PathVariable("id") String id) {
        return api.getPay(id);
    }
}
