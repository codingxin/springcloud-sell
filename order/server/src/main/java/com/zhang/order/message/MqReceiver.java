package com.zhang.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author codingzx
 * @description 接收mq消息
 * @date 2020/2/15 10:33
 */
@Slf4j
@Component
public class MqReceiver {

    // 1. 手动添加队列 @RabbitListener(queues = "myQueue")
    // 2.自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 3.自动创建队列
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }

    /**
     * 数码供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerQueue")
    ))
    public void processComputer(String message) {
        log.info("computer Receiver:{}", message);
    }

    /**
     * 数码供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitQueue")
    ))
    public void processFruit(String message) {
        log.info("fruit Receiver:{}", message);
    }

}
