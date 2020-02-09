package com.zhang.order.repository;

import com.netflix.discovery.converters.Auto;
import com.zhang.order.OrderApplication;
import com.zhang.order.OrderApplicationTests;
import com.zhang.order.entity.OrderMaster;
import com.zhang.order.enums.OrderStatusEnum;
import com.zhang.order.enums.PayStatusEnum;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("张大壮");
        orderMaster.setBuyerPhone("18229819406");
        orderMaster.setBuyerAddress("重庆南坪");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.New.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());


        OrderMaster result =orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result!=null);


    }


}