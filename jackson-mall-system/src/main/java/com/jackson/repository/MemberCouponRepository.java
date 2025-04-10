package com.jackson.repository;

import com.jackson.entity.ShopMemberCoupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberCouponRepository extends JpaRepository<ShopMemberCoupon, Long> {
    List<ShopMemberCoupon> findAllByCouponId(Long id);

    /**
     * 查询用户优惠卷
     *
     * @param userId     用户id
     * @param useStatus  用户是否使用
     * @param expireTime 优惠卷过期时间
     * @return
     */
    List<ShopMemberCoupon> findAllByUserIdAndUseStatusAndDelFlagAndExpireTimeAfter(Long userId, Short useStatus, Short delFlag, LocalDateTime expireTime);

    @Modifying
    @Transactional
    @Query("UPDATE ShopMemberCoupon s SET s.delFlag = :delFlag WHERE s.id IN (:ids)")
    void updateDelFlagByIds(@Param("ids") List<Long> ids, @Param("delFlag") Short delFlag);

    ShopMemberCoupon findByUserIdAndCouponId(Long userId, Long couponId);

    boolean existsByUserIdAndCouponId(Long userId, Long couponId);

    @Modifying
    @Transactional
    @Query("update ShopMemberCoupon s set s.useStatus = :useStatus where s.id in :idList")
    void updateAllUseStatus(@Param("idList") List<Long> idList, @Param("useStatus") Short useStatus);
}
