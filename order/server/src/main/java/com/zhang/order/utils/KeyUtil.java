package com.zhang.order.utils;

import java.util.Random;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 17:36
 */
public class KeyUtil {
    /**
     * 唯一主键生成： 时间+随机数
     * static 避免多线程生成同一个订单号
     */
    public   static synchronized String getUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(90000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
