package com.jackson.repository;

import com.jackson.entity.ShopMemberFollowStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFollowStoreRepository extends JpaRepository<ShopMemberFollowStore,Long> {
}
