package com.jing.cloud.controller;

import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/getStr/{key}")
    public Result<String> getStr(@PathVariable("key") String key) {
        Set keys = redisTemplate.keys("*");
        keys.forEach(System.out::println);
        Object result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return Result.fail(ReturnCodeEnum.RC500.getCode(), "获取失败");
        }
        return Result.success(result.toString());
    }
}
