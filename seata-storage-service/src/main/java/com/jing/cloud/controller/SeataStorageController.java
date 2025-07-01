package com.jing.cloud.controller;

import com.jing.cloud.resp.Result;
import com.jing.cloud.service.SeataStorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeataStorageController {
    @Resource
    private SeataStorageService seataStorageService;

    @PostMapping("/storage/decrease")
   public Result<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        seataStorageService.decrease(productId, count);
        return Result.success("扣减库存成功");
    }
}
