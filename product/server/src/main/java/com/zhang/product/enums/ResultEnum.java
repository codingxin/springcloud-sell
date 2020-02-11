package com.zhang.product.enums;

import lombok.Getter;

/**
 * @author codingzx
 * @description
 * @date 2020/2/9 15:03
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_NOTFULL(2,"库存不足"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
