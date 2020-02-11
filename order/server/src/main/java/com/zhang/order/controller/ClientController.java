package com.zhang.order.controller;

import com.zhang.order.dto.CartDTO;
import com.zhang.order.entity.ProductInfo;
import com.zhang.product.client.ProductClient;
import com.zhang.product.common.DecreaseStockInput;
import com.zhang.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/9 9:50
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getProductMSG")
    public String getProductMSG() {
        //1.第一种方式 指定IP
        RestTemplate restTemplate1 = new RestTemplate();
        String response = restTemplate1.getForObject("http://127.0.0.1:8080/msg", String.class);
        log.info("response={}", response);

        //2.第二种方式 LoadBalancerClient通过serviceId获取  即eureka注册的服务名称
        RestTemplate restTemplate2 = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        String response2 = restTemplate2.getForObject(url, String.class);
        log.info("response2={}", response2);

        //3.第三种方式 通过注入Bean和注解@LoadBalanced
       String response3= restTemplate.getForObject("http://PRODUCT/msg",String.class);
        log.info("response3={}", response3);

        // 通过FEIGN  类似 于 mq消息
        // 非RPC:  1.引入包 2.启动类配置注解 3.配置client接口类  基于接口的注解
//        String response4= productClient.productMsg();
//        log.info("response4={}", response4);
        return response3;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfoOutput> productInfoList=productClient.listForOrder(Arrays.asList("157875196366160022","157875227953464068"));
        log.info("response={}",productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        List<DecreaseStockInput> decreaseStockInputList;
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("157875196366160022",3),
                new DecreaseStockInput("157875227953464068",1)));
        return "ok";
    }
}
