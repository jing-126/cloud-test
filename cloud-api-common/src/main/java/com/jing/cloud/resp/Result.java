package com.jing.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {
    private String code;/** 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java*/
    private String message;
    private T data;
    private long timestamp;

    public Result() {
        this.timestamp=System.currentTimeMillis();
    }
    public static <T> Result<T> success(T data){
        Result<T> resultData = new Result<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode()).setMessage(ReturnCodeEnum.RC200.getMessage()).setData(data);
        return resultData;
    }
    public static <T> Result<T> fail(String code,String message){
        Result<T> resultData = new Result<>();
        resultData.setCode(code).setMessage(message).setData(null);
        return resultData;
    }
}
