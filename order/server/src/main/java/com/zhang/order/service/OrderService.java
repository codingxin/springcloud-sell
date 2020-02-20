package com.zhang.order.service;

import com.zhang.order.dto.OrderDTO;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 17:20
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家来操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
