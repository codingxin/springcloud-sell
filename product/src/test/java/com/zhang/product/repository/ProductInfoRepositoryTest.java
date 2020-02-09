package com.zhang.product.repository;

import com.zhang.product.entity.ProductInfo;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws  Exception{
        List<ProductInfo> list=productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size()>0);
    }
}