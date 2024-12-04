package com.jing.cloud.apis;

import com.jing.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-payment-service", path = "/pay")
public interface PayFeignApi {
    @GetMapping("/get/{id}")
    Result getPay(@PathVariable("id") String id);

    @GetMapping("/consul")
    Result getInfoByConsul();

    @GetMapping("/circuit/{id}")
    Result<String> myCircuit(@PathVariable("id") Integer id);

    @GetMapping("/bulkhead/{id}")
    Result<String> myBulkhead(@PathVariable("id") Integer id);

    @GetMapping("/poolBulkhead/{id}")
    Result<String> myPoolBulkhead(@PathVariable("id") Integer id);

    @GetMapping("/rateLimit/{id}")
    Result<String> myRateLimit(@PathVariable("id") Integer id);
}
