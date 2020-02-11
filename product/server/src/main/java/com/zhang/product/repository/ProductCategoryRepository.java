package com.zhang.product.repository;

import com.zhang.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:01
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,String> {
        List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
