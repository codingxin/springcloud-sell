package com.zhang.order.controller;

import com.zhang.order.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingzx
 * @description
 * @date 2020/2/14 11:04
 */
@RestController
public class GirlController {
    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/girl/print")
    public String print() {
        return "name:" + girlConfig.getName() + "age:" + girlConfig.getAge();
    }
}
