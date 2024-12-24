package com.jing.cloud.apis.fallBack;

import com.jing.cloud.apis.PayFeignApi;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class PayFeignApiFallBack implements PayFeignApi {
    @Override
    public Result getPay(String id) {
        return null;
    }

    @Override
    public Result getInfoByConsul() {
        return null;
    }

    @Override
    public Result<String> myCircuit(Integer id) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> myBulkhead(Integer id) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> myPoolBulkhead(Integer id) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> myRateLimit(Integer id) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> payMicrometer(String id) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<PayDTO> getById(Integer id) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> getGatewayInfo() {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> getZonedDatetime() {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }

    @Override
    public Result<String> getGatewayFilter(HttpServletRequest request) {
        return Result.fail(ReturnCodeEnum.RC404.getCode(), "该服务不可用");
    }
}
