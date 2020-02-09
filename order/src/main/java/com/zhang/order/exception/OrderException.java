package com.zhang.order.exception;

import com.zhang.order.enums.ResultEnum;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 17:50
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
