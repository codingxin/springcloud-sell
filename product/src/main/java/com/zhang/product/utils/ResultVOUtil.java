package com.zhang.product.utils;

import com.zhang.product.VO.ResultVO;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 15:09
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
