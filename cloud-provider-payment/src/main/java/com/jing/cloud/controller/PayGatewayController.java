package com.jing.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.jing.cloud.entities.Pay;
import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import com.jing.cloud.service.PayService;
import com.jing.cloud.utils.DateTimeZoneUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
@RequestMapping("/pay/gateway")
public class PayGatewayController {
    @Resource
    private PayService service;

    @Value("${server.port}")
    private String port;

    @GetMapping("/get/{id}")
    public Result<PayDTO> getById(@PathVariable("id") Integer id) {
        Pay pay = service.getById(id);
        PayDTO dto = new PayDTO();
        BeanUtil.copyProperties(pay, dto);
        dto.setServerPort(port);
        return Result.success(dto);
    }

    @GetMapping("/info")
    public Result<String> getGatewayInfo() {
        return Result.success("gateway info test:" + IdUtil.simpleUUID() + "port:" + port);
    }

    @GetMapping("/getZoned")
    public Result<String> getZonedDatetime() {
        return Result.success(DateTimeZoneUtil.getZonedDateTime());
    }

    @GetMapping("/filter")
    public Result<String> getGatewayFilter(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("请求头：" + headerName + "，请求头值" + headerValue);
            if (headerName.equalsIgnoreCase("X-Request-Test") || headerName.equalsIgnoreCase("X-Request-Jing")) {
                result.append("headerName:").append(headerName).append("headerValue:").append(headerValue).append("\r\n");
            }
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            System.out.println("请求参数" + parameterName + ":" + parameterValue);
        }
        String customerName = request.getParameter("customerName");
        System.out.println("customerName:" + customerName);
        return Result.success("gatewayFilter 过滤器 test:" + result + DateUtil.now());
    }
}
