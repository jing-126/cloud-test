package com.jing.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlowLimitService {

    @SentinelResource("common")
    public void common() {
        log.info("--------FlowLimitService come in --------------------");
    }
}
