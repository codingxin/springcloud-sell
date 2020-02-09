package com.zhang.product.dto;

import lombok.Data;

/**
 * @author codingzx
 * @description
 * @date 2020/2/9 14:37
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
