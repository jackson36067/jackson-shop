package com.jackson.repository;

import com.jackson.entity.ShopMemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberCouponRepository extends JpaRepository<ShopMemberCoupon,Long> {
    List<ShopMemberCoupon> findAllByCouponId(Long id);
}
