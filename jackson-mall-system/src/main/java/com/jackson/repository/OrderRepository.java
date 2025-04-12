package com.jackson.repository;

import com.jackson.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<ShopOrder, Long>, JpaSpecificationExecutor<ShopOrder> {
}
