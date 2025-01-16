package com.jing.cloud.apis;

import com.jing.cloud.apis.fallBack.PaymentServiceFeignApiFallBack;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-provider-payment", fallback = PaymentServiceFeignApiFallBack.class)
public interface PaymentServiceFeignApi {
    @GetMapping(value = "/nacos/get/{orderNo}")
    Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
