package com.jackson.repository;

import com.jackson.entity.ShopCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<ShopCoupon, Long> {
}
