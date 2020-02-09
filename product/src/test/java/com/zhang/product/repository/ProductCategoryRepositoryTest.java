package com.zhang.product.repository;

import com.zhang.product.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public  class ProductCategoryRepositoryTest {
   @Autowired
   private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<ProductCategory> listproduct=productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,12));
        Assert.assertTrue(listproduct.size()>0);
    }
}