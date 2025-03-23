package com.jackson.repository;

import com.jackson.entity.ShopMemberFollowStore;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface MemberFollowStoreRepository extends JpaRepository<ShopMemberFollowStore,Long> {
    // 根据用户id以及店铺id,判断用户是否关注店铺
    Boolean existsByMemberIdAndStoreId(Long memberId,Long storeId);
    // 获取店铺的粉丝数量
    Integer countByStoreId(Long storeId);
    // 删除用户关注店铺记录
    @Modifying
    @Transactional
    void deleteByMemberIdAndStoreId(Long memberId,Long storeId);
    // 获取用户关注店铺列表
    List<ShopMemberFollowStore> findAllByMemberId(Long memberId);
}
