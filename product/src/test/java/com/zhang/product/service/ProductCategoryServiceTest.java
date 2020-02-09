package com.zhang.product.service;

import com.zhang.product.ProductApplicationTests;
import com.zhang.product.entity.ProductCategory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class ProductCategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<ProductCategory> listcategory=categoryService.findByCategoryTypeIn(Arrays.asList(11,12));
        Assert.assertTrue(listcategory.size()>0);
    }
}