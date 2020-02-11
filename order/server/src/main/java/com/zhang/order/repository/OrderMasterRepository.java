package com.zhang.order.repository;

import com.zhang.order.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author codingzx
 * @description
 * @date 2020/2/8 16:21
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

}
