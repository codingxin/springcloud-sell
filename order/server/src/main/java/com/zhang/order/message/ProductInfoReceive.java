package com.zhang.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhang.order.utils.JsonUtil;
import com.zhang.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/16 13:56
 */
@Component
@Slf4j
public class ProductInfoReceive {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 商品服务端扣减库存 - 发送消息
     * 订单客户端接收消息 - 扣减库存
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message->>productInfoOutput
        //message->>productInfoOutputList
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {
                });
        log.info("从队列{}接收到消息{}", "productInfo", productInfoOutputList);

        //存储到Redis里   key,value
        // String.format: System.out.println(String.format("今天的日期是:%tD", new java.util.Date()));
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    (String.valueOf(productInfoOutput.getProductStock())));
        }

    }
}
