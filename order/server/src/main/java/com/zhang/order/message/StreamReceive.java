package com.zhang.order.message;

import com.zhang.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author codingzx
 * @description
 * @date 2020/2/16 9:53
 */

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceive {

//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceive：{}", message);
//    }

    /**
     * 消费端： 接收orderDTO对象
     * @param message
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO message) {
        log.info("StreamReceive：{}", message);
        //发送Mq消息 process2接收
        return "revied MQ";
    }
    @StreamListener(StreamClient.INPUT2)
    public void process2(String message) {
        log.info("StreamReceive2：{}", message);
        //回应Mq消息
    }

}
