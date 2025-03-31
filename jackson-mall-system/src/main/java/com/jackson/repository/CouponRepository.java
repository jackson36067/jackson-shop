package com.jackson.repository;

import com.jackson.entity.ShopCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponRepository extends JpaRepository<ShopCoupon, Long> {
    List<ShopCoupon> findAllByShopStore_IdAndExpireTimeAfter(Long shopStoreId, LocalDateTime expireTime);

    @Query("select count(*) from ShopCoupon s where s.id = :couponId and s.num > 0")
    int isCouponHaveNum(long couponId);

    List<ShopCoupon> findAllByTypeAndExpireTimeBefore(Short type, LocalDateTime expireTime);
}
