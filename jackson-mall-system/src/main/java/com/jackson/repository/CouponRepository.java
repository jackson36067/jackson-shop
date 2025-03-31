package com.jackson.repository;

import com.jackson.entity.ShopCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<ShopCoupon, Long> {
    @Query("select sc from ShopCoupon sc where sc.shopStore.id = :id")
    List<ShopCoupon> findAllByShopStoreId(Long id);

    @Query("select count(*) from ShopCoupon s where s.id = :couponId and s.num > 0")
    int isCouponHaveNum(long couponId);
}
