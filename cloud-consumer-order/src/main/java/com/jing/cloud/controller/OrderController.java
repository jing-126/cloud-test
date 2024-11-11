package com.jing.cloud.controller;

import com.jing.cloud.entities.PayDTO;
import com.jing.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

//    private static String BASE_URL = "http://localhost:8001";
    private static String BASE_URL = "http://cloud-payment-service";

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

    @GetMapping("/pay/consul")
    public Result getConsulInfo() {
        return restTemplate.getForObject(BASE_URL + "/pay/consul", Result.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);
        System.out.println("-----------------------------------------");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + ":" + instance.getHost() + ":" + instance.getPort() + ":" + instance.getUri());
        }
        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }

}
