package com.jackson.repository;

import com.jackson.entity.ShopGood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GoodsRepository extends JpaRepository<ShopGood, Long>, JpaSpecificationExecutor<ShopGood> {

    // 通过分类 ID 查询所有商品，并支持分页
    Page<ShopGood> findAllByShopCategoryId(Long categoryId, Pageable pageable);
}
