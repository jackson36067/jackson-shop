package com.jackson.repository;

import com.jackson.entity.ShopMemberFollowStore;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface MemberFollowStoreRepository extends JpaRepository<ShopMemberFollowStore,Long> {
    Boolean existsByMemberIdAndStoreId(Long memberId,Long storeId);
    Integer countByStoreId(Long storeId);
    @Modifying
    @Transactional
    void deleteByMemberIdAndStoreId(Long memberId,Long storeId);
}
