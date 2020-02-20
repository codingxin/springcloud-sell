package com.zhang.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author codingzx
 * @description
 * @date 2020/2/20 10:01
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    // @HystrixCommand(fallbackMethod = "fallback")
    //超时熔断@HystrixCommand(commandProperties = {
    //       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),   //设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    @GetMapping("getProductInfoList")
//    @HystrixCommand
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8080/product/listForOrder", Arrays.asList("157875196366160022"), String.class);
    }

    private String fallback() {
        return "服务拥挤，请稍后在尝试访问";
    }

    private String defaultFallback() {
        return "默认提示服务拥挤，请稍后在尝试访问";
    }
}
