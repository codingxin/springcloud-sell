package com.zhang.product.client;



import com.zhang.product.common.DecreaseStockInput;
import com.zhang.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/9 11:16
 */
@FeignClient(name = "product")
public interface ProductClient {



    /**
     * RequestBody注解必须用postmapping和服务端一致
     *
     * @param productIdList
     * @return
     * @pathvalid 可以用getmappeing
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
//    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
