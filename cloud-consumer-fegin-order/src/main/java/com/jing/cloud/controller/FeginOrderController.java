package com.jing.cloud.controller;

import cn.hutool.core.date.DateUtil;
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
        Result result = null;
        try {
            System.out.println("远程调用查询支付信息，当前时间：" + DateUtil.now());
            result = api.getPay(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("远程调用发生异常，当前时间：" + DateUtil.now());
        }
        return result;
    }
}
