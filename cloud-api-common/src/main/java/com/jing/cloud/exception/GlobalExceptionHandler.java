package com.jing.cloud.exception;

import com.jing.cloud.resp.Result;
import com.jing.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(RuntimeException e) {
        log.error("全局异常信息：{}",e.getMessage(), e);
        return Result.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }
}
