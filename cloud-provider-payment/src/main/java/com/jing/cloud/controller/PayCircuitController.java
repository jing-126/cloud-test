package com.jing.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.jing.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
public class PayCircuitController {

    @GetMapping("/circuit/{id}")
    public Result<String> myCircuit(@PathVariable("id") Integer id) {
        return this.message(id);
    }

    @GetMapping("/bulkhead/{id}")
    public Result<String> myBulkhead(@PathVariable("id") Integer id) {
        return this.message(id);
    }

    @GetMapping("/poolBulkhead/{id}")
    public Result<String> myPoolBulkhead(@PathVariable("id") Integer id) {
        return this.message(id);
    }

    @GetMapping("/rateLimit/{id}")
    public Result<String> myRateLimit(@PathVariable("id") Integer id) {
        return Result.success("Hello, myRateLimit Test inputId：" + id + "\r\n" + IdUtil.simpleUUID());
    }

    private Result<String> message(Integer id) {
        if (id == -4) throw new RuntimeException("circuit id 不能为负数");
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
//        return "circuit id : " + id + IdUtil.simpleUUID();
        return Result.success("circuit id : " + id + IdUtil.simpleUUID());
    }
}
