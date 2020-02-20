package com.zhang.order.repository;

import com.zhang.order.entity.OrderDetail;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 16:21
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {


    List<OrderDetail> findByOrderId(String orderId);
}
