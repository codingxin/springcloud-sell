package com.zhang.order.utils;

import com.zhang.order.VO.ResultVO;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 18:30
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(1);
        resultVO.setData(object);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
