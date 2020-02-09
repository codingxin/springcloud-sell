package com.zhang.product.service;

import com.zhang.product.entity.ProductCategory;
import com.zhang.product.entity.ProductInfo;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:24
 */
public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
}
