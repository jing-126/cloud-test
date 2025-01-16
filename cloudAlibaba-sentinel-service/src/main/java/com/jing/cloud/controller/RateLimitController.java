package com.jing.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RateLimitController {
    @GetMapping("testRest")
    public String testRest() {
        return "testRest 限流 success";
    }

    @GetMapping("testResource")
    @SentinelResource(value = "testResourceBlock", blockHandler = "resourceBlockHandler") //默认抛出Whitelabel Error Page
    public String testResource() {
        return "testResource 限流 success";
    }

    private String resourceBlockHandler(BlockException ex) {
        return "testResource 自定义提示";
    }

    @GetMapping("/testResourceFallback/{id}")
    @SentinelResource(value = "testResourceFallback", blockHandler = "resourceFallbackBlockHandler", fallback = "resourceFallbackFallback") //默认抛出Whitelabel Error Page
    public String testResourceFallback(@PathVariable("id") Integer id) {
        if (id == 0) {
            int i = 1/0;
        }
        return "testResourceFallback 限流 success";
    }

    private String resourceFallbackBlockHandler(@PathVariable("id") Integer id, BlockException ex) {
        return "testResourceFallback 自定义提示";
    }

    private String resourceFallbackFallback(@PathVariable("id") Integer id, Throwable e) {
        return "程序异常。。。。";
    }

    @GetMapping("testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "testHotKeyBlock")
    public String testHotKey(@RequestParam(value = "key", required = false) String key, @RequestParam(value = "key2", required = false) String key2) {
        return "testHotKeyTest success...";
    }

    private String testHotKeyBlock(@RequestParam(value = "key", required = false) String key,
                                   @RequestParam(value = "key2", required = false) String key2,
                                   BlockException ex) {
        return "testHotKeyBlock 热点限流。。。。";
    }
}
