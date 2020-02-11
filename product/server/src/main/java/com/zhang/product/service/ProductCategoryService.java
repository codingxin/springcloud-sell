package com.zhang.product.service;

import com.zhang.product.entity.ProductCategory;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:24
 */
public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
}
