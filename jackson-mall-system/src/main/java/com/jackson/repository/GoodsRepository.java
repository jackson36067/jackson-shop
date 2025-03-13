package com.jackson.repository;

import com.jackson.entity.ShopGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsRepository extends JpaRepository<ShopGood, Long>, JpaSpecificationExecutor<ShopGood> {
}
