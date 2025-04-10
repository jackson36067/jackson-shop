package com.jackson.repository;

import com.jackson.entity.ShopGoodsProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsProductRepository extends JpaRepository<ShopGoodsProduct, Long> {
    ShopGoodsProduct findByIdAndNumberAfter(Long id, Integer number);
}
