package com.zhang.product.excepticon;

import com.zhang.product.entity.ProductInfo;
import com.zhang.product.enums.ResultEnum;

/**
 * @author codingzx
 * @description
 * @date 2020/2/9 15:07
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code,String message){
        super(message);
        this.code=code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
}
