package com.zhang.order.controller;

import java.util.HashMap;
import java.util.Map;

import com.zhang.order.VO.ResultVO;
import com.zhang.order.converter.OrderFormTOorderDTO;
import com.zhang.order.dto.OrderDTO;
import com.zhang.order.enums.ResultEnum;
import com.zhang.order.exception.OrderException;
import com.zhang.order.form.OrderForm;
import com.zhang.order.service.OrderService;
import com.zhang.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 17:16
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class Ordercontroller {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查验商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[订单参数不正确]，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm->orderDto
        OrderDTO orderDTO = OrderFormTOorderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    @PostMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));
    }

}
