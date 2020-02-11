package com.zhang.product.service.impl;

import com.zhang.product.dto.CartDTO;
import com.zhang.product.entity.ProductInfo;
import com.zhang.product.enums.ResultEnum;
import com.zhang.product.excepticon.ProductException;
import com.zhang.product.repository.ProductInfoRepository;
import com.zhang.product.enums.ProductStatusEnum;
import com.zhang.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:26
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findupAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.Up.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    /**
     * 入参是购物车list
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfooptional = productInfoRepository.findById(cartDTO.getProductId());
            //判断商品是否存在
            if (!productInfooptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfooptional.get();
            // 商品库存 -  购物车该商品库存是否足够
            Integer result=productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_NOTFULL);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
