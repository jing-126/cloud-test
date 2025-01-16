package com.jing.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmpowerLimitController {

    @GetMapping("/empower")
    public String empowerLimit() {
        log.info("empowerLimit test");
        return "sentinel 授权规则test";
    }
}
