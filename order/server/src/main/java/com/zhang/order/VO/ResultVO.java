package com.zhang.order.VO;

import lombok.Data;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 10:14
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

}
