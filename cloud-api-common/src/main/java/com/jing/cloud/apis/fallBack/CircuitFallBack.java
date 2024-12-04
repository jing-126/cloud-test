package com.jing.cloud.apis.fallBack;

import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;

public class CircuitFallBack {
    public Result<String> myCircuitFallBack(Integer id, Throwable throwable) {
        return Result.fail(ReturnCodeEnum.RC201.getCode(),"id:" + id + "myCircuitFallBack，系统繁忙，请稍后再试。。。。" + throwable.getMessage());
    }
}
