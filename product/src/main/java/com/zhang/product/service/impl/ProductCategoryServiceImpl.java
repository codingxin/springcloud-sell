package com.zhang.product.service.impl;

import com.zhang.product.entity.ProductCategory;
import com.zhang.product.repository.ProductCategoryRepository;
import com.zhang.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:48
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryList);
    }
}
