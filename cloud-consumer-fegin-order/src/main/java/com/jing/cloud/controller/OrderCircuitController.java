package com.jing.cloud.controller;

import com.jing.cloud.apis.PayFeignApi;
import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi feignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallBack")
    public Result<String> myCircuitBreaker(@PathVariable("id") Integer id) {
        return feignApi.myCircuit(id);
    }

    public Result<String> myCircuitFallBack(Integer id, Throwable throwable) {
        return Result.fail(ReturnCodeEnum.RC201.getCode(),"myCircuitFallBack，系统繁忙，请稍后再试。。。。" + throwable.getMessage() + "id:" + id);
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallBack", type = Bulkhead.Type.SEMAPHORE)
    public Result<String> myBulkhead(@PathVariable("id") Integer id) {
        return feignApi.myBulkhead(id);
    }

    public Result<String> myBulkheadFallBack(Integer id, Throwable throwable) {
        return Result.fail(ReturnCodeEnum.RC201.getCode(),"myBulkheadFallBack，超出最大数量限制，系统繁忙，请稍后再试。。。。" + throwable.getMessage() + "id:" + id);
    }

    @GetMapping("/feign/pay/poolBulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myPoolBulkheadFallBack", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Result<String>> myPoolBulkhead(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "---------开始进入----------");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "---------准备离开----------");
        return CompletableFuture.supplyAsync(() -> feignApi.myPoolBulkhead(id));
    }

    public CompletableFuture<Result<String>> myPoolBulkheadFallBack(Integer id, Throwable throwable) {
        return CompletableFuture.supplyAsync(() ->
                Result.fail(ReturnCodeEnum.RC201.getCode(),"myPoolBulkheadFallBack，超出最大数量限制，系统繁忙，请稍后再试。。。。" + throwable.getMessage() + "id:" + id));
    }

    @GetMapping("/feign/pay/rateLimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimiterFallBack")
    public Result<String> myRateLimiter(@PathVariable("id") Integer id) {
        return feignApi.myRateLimit(id);
    }

    public Result<String> myRateLimiterFallBack(Integer id, Throwable throwable) {
        return Result.fail(ReturnCodeEnum.RC201.getCode(),"超出最大访问限制，系统繁忙，请稍后再试。。。。" + throwable.getMessage() + "id:" + id);
    }
}
