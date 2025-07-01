package com.jing.cloud.apis;

import com.jing.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 仓储服务
 */
@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    /**
     * 扣减库存
     */
    @PostMapping("/storage/decrease")
    Result<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
