package com.zhang.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    Up(0,"在架"),
    Down(1,"下架"),
    ;
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }




}
