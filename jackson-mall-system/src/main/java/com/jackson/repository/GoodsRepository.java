package com.jackson.repository;

import com.jackson.entity.ShopGood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<ShopGood, Long>, JpaSpecificationExecutor<ShopGood> {

    // 通过分类 ID 查询所有商品，并支持分页
    Page<ShopGood> findAllByShopCategoryId(Long categoryId, Pageable pageable);

    List<ShopGood> findAllByIdInAndNameLike(List<Long> ids, String goodsName);

    Page<ShopGood> findAllByShopStore_Id(Long storeId, Pageable pageable);

    @Query("select s from ShopGood s where s.id not in :idList order by rand()")
    Page<ShopGood> findRandomShopGoodsExcludingIds(List<Long> idList, Pageable pageable);
}
