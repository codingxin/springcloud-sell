package com.zhang.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhang.order.entity.OrderDetail;
import com.zhang.order.enums.ResultEnum;
import com.zhang.order.exception.OrderException;
import com.zhang.order.dto.OrderDTO;
import com.zhang.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 17:58
 */
@Slf4j
public class OrderFormTOorderDTO {

    /**
     * 构造返回前端的结构体
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        /**
         * 构造JSON对象
         */
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("[JSON转换]错误,string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }


}
