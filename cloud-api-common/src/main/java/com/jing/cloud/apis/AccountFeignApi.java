package com.jing.cloud.apis;

import com.jing.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-account-service")
public interface AccountFeignApi {
    @PostMapping("/account/decrease")
    Result<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
