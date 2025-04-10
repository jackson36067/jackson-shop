package com.jackson.repository;

import com.jackson.entity.ShopOrderGoods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGoodsRepository extends JpaRepository<ShopOrderGoods,Long> {
}
