package com.jackson.repository;

import com.jackson.entity.ShopGood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<ShopGood, Long> {
}
