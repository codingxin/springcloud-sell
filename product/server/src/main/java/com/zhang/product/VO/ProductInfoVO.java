package com.zhang.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 9:53
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
   private String productId;

    @JsonProperty("name")
   private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("icon")
    private String productIcon;

    @JsonProperty("Description")
    private String productDescription;
}
