package com.zhang.product.service;

import com.zhang.product.common.DecreaseStockInput;
import com.zhang.product.dto.CartDTO;
import com.zhang.product.entity.ProductInfo;
import javassist.runtime.Desc;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:24
 */
public interface ProductService {
    /**
     * 查询所有在架的商品列表
     * @return
     */
    List<ProductInfo> findupAll();

    /**
     * 查询商品
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣除库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
