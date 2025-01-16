package com.jing.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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

    // sentinel+openFeign 进行服务降级和流量监控的整合处理
    @GetMapping("/get/{orderNo}")
    @SentinelResource(value = "getByOrderNo", blockHandler = "handlerBlockHandler")
    public Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        PayDTO payDTO = new PayDTO();
        payDTO.setOrderNo(orderNo);
        payDTO.setId(1024);
        payDTO.setAmount(BigDecimal.valueOf(123.45));
        payDTO.setPayNo("pay:" + IdUtil.fastUUID());
        payDTO.setUserId(1);
        return Result.success(payDTO);
    }

    private Result<PayDTO> handlerBlockHandler(@PathVariable("orderNo") String orderNo, BlockException ex) {
        return Result.fail(ReturnCodeEnum.RC500.getCode(), "getPayByOrderNo服务不可用，触发sentinel流控配置规则");
    }
}
