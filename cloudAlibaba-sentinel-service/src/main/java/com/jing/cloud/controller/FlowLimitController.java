package com.jing.cloud.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jing.cloud.service.FlowLimitService;
import com.sun.tools.javac.Main;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/flow")
public class FlowLimitController {
    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "----------testA-------------";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----------testB-------------";
    }

    /**
     * testC与testD都访问flowLimitService.common()方法，阈值到达后对C进行限流，D正常访问
     * @return
     */

    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "----------testC-------------";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "----------testD-------------";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("流控效果：排队等待。。。。{}", System.currentTimeMillis());
        return "----------testE-------------";
    }

    @GetMapping("/testF")
    public String testF() {
        log.info("熔断->慢调用比例{}", System.currentTimeMillis());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return "----------testF 新增熔断规划-慢调用比例-------------";
    }

    @GetMapping("/testG")
//    @SentinelResource("testG")
    public String testG() {
        log.info("熔断->异常比例{}", System.currentTimeMillis());
        Random random = new Random();
        random.nextInt(10);
        if (random.nextInt(10) > 5) {
            throw new NumberFormatException("testG");
        }
        return "----------testF 新增熔断规划-异常比例-------------";
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
