package com.jing.cloud.apis;

import com.jing.cloud.apis.fallBack.PayFeignApiFallBack;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "cloud-payment-service", path = "/pay")
@FeignClient(value = "cloud-gateway", path = "/pay", fallback = PayFeignApiFallBack.class)
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

    @GetMapping("/micrometer/{id}")
    Result<String> payMicrometer(@PathVariable("id") String id);

    @GetMapping("/gateway/get/{id}")
    Result<PayDTO> getById(@PathVariable("id") Integer id);

    @GetMapping("/gateway/info")
    Result<String> getGatewayInfo();

    @GetMapping("/gateway/getZoned")
    Result<String> getZonedDatetime();

    @GetMapping("/gateway/filter")
    Result<String> getGatewayFilter(HttpServletRequest request);
}
