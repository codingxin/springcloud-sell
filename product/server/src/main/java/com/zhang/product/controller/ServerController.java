package com.zhang.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingzx
 * @description
 * @date 2020/2/9 9:44
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "this is product' msg";
    }
}
