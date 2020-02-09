package com.zhang.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 9:51
 */
@Data
public class ProductVO {
    //使用 @JsonProperty注解将表结构中的字段映射到实体类中
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;


}
