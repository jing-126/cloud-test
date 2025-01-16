package com.jing.cloud.apis.fallBack;

import com.jing.cloud.apis.PaymentServiceFeignApi;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFeignApiFallBack implements PaymentServiceFeignApi {
    @Override
    public Result<PayDTO> getPayByOrderNo(String orderNo) {
        return Result.fail(ReturnCodeEnum.RC500.getCode(), "被调用服务宕机或不可用，服务降级。。。");
    }
}
