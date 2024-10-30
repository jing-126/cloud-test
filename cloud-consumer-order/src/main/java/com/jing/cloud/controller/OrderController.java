package com.jing.cloud.controller;

import com.jing.cloud.entities.PayDTO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    private static String BASE_URL = "http://localhost:8001";

    @PostMapping("/pay/add")
    public String addOrder(PayDTO dto) {
        String s = restTemplate.postForObject(BASE_URL + "/pay/add", dto, String.class);
        return "success";
    }

    @PostMapping("/pay/update")
    public String updateOrder(PayDTO dto) {
        restTemplate.exchange(BASE_URL + "/pay/update", HttpMethod.PUT, new HttpEntity<PayDTO>(dto), String.class);
        return "success";
    }

    @GetMapping("/pay/get/{id}")
    public String getOrder(@PathVariable("id") int id) {
        return restTemplate.getForObject(BASE_URL + "/pay/get/" + id, String.class);
    }

    @DeleteMapping("/pay/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        restTemplate.exchange(BASE_URL + "/pay/delete/" + id, HttpMethod.DELETE, null, String.class);
        return "success";
    }

}
