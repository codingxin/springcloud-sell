package com.zhang.product.service.impl;

import com.rabbitmq.tools.json.JSONUtil;
import com.zhang.product.common.DecreaseStockInput;
import com.zhang.product.common.ProductInfoOutput;
import com.zhang.product.dto.CartDTO;
import com.zhang.product.entity.ProductInfo;
import com.zhang.product.enums.ResultEnum;
import com.zhang.product.excepticon.ProductException;
import com.zhang.product.repository.ProductInfoRepository;
import com.zhang.product.enums.ProductStatusEnum;
import com.zhang.product.service.ProductService;
import com.zhang.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.json.JsonPathUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author codingzx
 * @description
 * @date 2020/2/7 20:26
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

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
     *
     * @param decreaseStockInputList
     */
    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        //发送mq消息
        //购物车单个对象转为List
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(
                e -> {
                    ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, productInfoOutput);
                    return productInfoOutput;
                }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> productInfooptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfooptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfooptional.get();
            // 商品库存 -  购物车该商品库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_NOTFULL);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}

