package com.zhang.order.controller;


import com.zhang.order.dto.OrderDTO;
import com.zhang.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingzx
 * @description
 * @date 2020/2/16 9:56
 */
@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        //MessageBuilder.withPayload(消息内容).build()
//        String message = "now Time:" + new Date();
//        streamClient.output().send(MessageBuilder.withPayload(message).build());
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderId("1234567");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
