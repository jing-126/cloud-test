package com.jing.cloud.exception;

import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoFallbackAvailableException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<String> executionException(NoFallbackAvailableException e) {
        System.out.println(e.getMessage());
        return Result.fail(ReturnCodeEnum.RC403.getCode(), ReturnCodeEnum.RC403.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(RuntimeException e) {
        log.error("全局异常信息：{}",e.getMessage(), e);
        return Result.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }
}
