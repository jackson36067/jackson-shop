package com.jackson.repository;

import com.jackson.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ShopOrder, Long> {
}
