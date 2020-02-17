package com.zhang.product.controller;

import com.zhang.product.VO.ProductInfoVO;
import com.zhang.product.VO.ResultVO;
import com.zhang.product.common.DecreaseStockInput;
import com.zhang.product.entity.ProductCategory;
import com.zhang.product.utils.ResultVOUtil;
import com.zhang.product.VO.ProductVO;
import com.zhang.product.dto.CartDTO;
import com.zhang.product.entity.ProductInfo;
import com.zhang.product.service.ProductCategoryService;
import com.zhang.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品控制逻辑
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 1. 查询所有
     * 2.获取类目type
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
        //1.查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findupAll();

        //2.获取所有类目type列表
        List<Integer> productCategoryList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3.从数据库查询类目
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(productCategoryList);

        //4.构造返回体
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品（给订单服务用）
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }

}
