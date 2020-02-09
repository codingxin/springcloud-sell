package com.zhang.product.service;


import com.zhang.product.ProductApplicationTests;
import com.zhang.product.dto.CartDTO;
import com.zhang.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceTest extends ProductApplicationTests {
    @Autowired
    private ProductService productService;

    @Test
    public void findupAll() throws Exception {
        List<ProductInfo> listProduct = productService.findupAll();
        Assert.assertTrue(listProduct.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception {
        CartDTO cartDTO = new CartDTO("157875196366160022",1);
        productService.decreaseStock(Arrays.asList(cartDTO));

    }


}