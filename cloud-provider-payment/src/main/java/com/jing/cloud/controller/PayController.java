package com.jing.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jing.cloud.entities.Pay;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import com.jing.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay")
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串做参数")
    public Result<String> addPay(@RequestBody Pay pay) {
        int add = payService.add(pay);
        return Result.success("新增成功：" + add);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public Result<String> deletePay(@PathVariable("id") int id) {
        return Result.success("删除成功：" + payService.delete(id));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "查询", description = "根据ID查询支付流水")
    public Result<Pay> getPay(@PathVariable("id") int id) {
        return Result.success(payService.getById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "更新", description = "更新支付流水")
    public Result<String> updatePay(@RequestBody PayDTO dto) {
        Pay pay = new Pay();
        BeanUtil.copyProperties(dto, pay);
        int update = payService.update(pay);
        return Result.success("更新成功：" + update);
    }

    @GetMapping("/get/list")
    @Operation(summary = "查询", description = "查询所有支付流水方法")
    public Result<List<Pay>> getPayList() {
        return Result.success(payService.getAll());
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/consul")
    @Operation(summary = "查询", description = "consul配置")
    public Result<String> getInfoByConsul(@Value("${jing.info}") String info) {
        String consulInfo = "jing.info: " + info + "\t" + "port" + port;
        return Result.success(consulInfo);
    }
}
