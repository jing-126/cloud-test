package com.jing.cloud.controller;

import com.jing.cloud.entities.Order;
import com.jing.cloud.resp.Result;
import com.jing.cloud.service.SeataOrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Resource
    private SeataOrderService service;

    @GetMapping("/order/create")
    public Result<Order> create(Order order) {
        service.createOrder(order);
        return Result.success(order);
    }
}
