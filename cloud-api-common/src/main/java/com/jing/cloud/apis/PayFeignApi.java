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
}
