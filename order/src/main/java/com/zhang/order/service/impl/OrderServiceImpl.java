package com.zhang.order.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zhang.order.client.ProductClient;
import com.zhang.order.dto.CartDTO;
import com.zhang.order.dto.OrderDTO;
import com.zhang.order.entity.OrderDetail;
import com.zhang.order.entity.OrderMaster;
import com.zhang.order.entity.ProductInfo;
import com.zhang.order.enums.OrderStatusEnum;
import com.zhang.order.enums.PayStatusEnum;
import com.zhang.order.repository.OrderDetailRepository;
import com.zhang.order.repository.OrderMasterRepository;
import com.zhang.order.service.OrderService;
import com.zhang.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author codingzx
 * @description
 * 订单入库功能
 * 客户端：
 * 1.参数校验
 * 2.查询购物车中所有商品 -  构造商品idList 调用服务端接口查询所有商品productInfoList
 * 3.计算价格
 *   循环购物车商品list—》ordertail  和 productInfoList -》productInfo，新建订单详情ordertail
 *   当id相等 计算价格 ，把productInfo复制到订单详情ordertail
 *   订单详情入库
 * 4.扣减库存
 * 调用服务端接口
 * 设置订单状态
 * 订单入库
 *
 *
 *
 *
 *
 * 服务端：
 * 先遍历购物车对象，通过遍历找到每个商品
 * 判断商品是否存在
 * 更新库存：商品库存-购物车商品数量
 * @date 2020/2/8 17:26
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        // 1. 查询商品信息
        //lam表达式 获取 orderDto.orderList里面所有的id，并返回List
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        //  * 2.  计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //先循环订单中所有商品
            for (ProductInfo productInfo : productInfoList) {
                //在循环订单里面有的商品信息
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //判斷是取到商品的價格  总价=单价*数量
                    orderAmout = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmout);
                    //前端只传商品id和商品数量 其他属性需要拷贝
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //  * 3. 扣库存  lam表达式获取对象  获取订单里面所有的cartDto对象 进行扣减库存
        List<CartDTO> cartDtoList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);

        //  * 4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setUpdateTime(new Date());
        orderMaster.setCreateTime(new Date());
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.New.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

}
