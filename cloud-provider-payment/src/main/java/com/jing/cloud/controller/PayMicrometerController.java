package com.jing.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.jing.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/micrometer")
public class PayMicrometerController {
    @GetMapping("/{id}")
    public Result<String> payMicrometer(@PathVariable("id") String id) {
        return Result.success("Test链路追踪，Micrometer inputId:" + id + "----服务返回" + IdUtil.simpleUUID());
    }
}
