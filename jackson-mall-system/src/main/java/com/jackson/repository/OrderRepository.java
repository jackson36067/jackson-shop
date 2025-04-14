package com.jackson.repository;

import com.jackson.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<ShopOrder, Long>, JpaSpecificationExecutor<ShopOrder> {

    // 获取用户订单,根据订单状态分组,获取每个状态下订单的数量
    @Query("SELECT o.orderStatus, COUNT(o) FROM ShopOrder o where o.userId = :userId GROUP BY o.orderStatus")
    List<Object[]> findOrderCountByUserIdGroupByStatus(Long userId);
}
