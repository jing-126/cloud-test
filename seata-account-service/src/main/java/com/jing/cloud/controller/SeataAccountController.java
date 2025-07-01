package com.jing.cloud.controller;

import com.jing.cloud.resp.Result;
import com.jing.cloud.service.SeataAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeataAccountController {

    @Resource
    private SeataAccountService seataAccountService;

    @PostMapping("/account/decrease")
    Result<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
        seataAccountService.decrease(userId, money);
        return Result.success("余额扣减成功");
    }
}
