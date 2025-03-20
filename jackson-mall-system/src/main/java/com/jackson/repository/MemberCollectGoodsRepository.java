package com.jackson.repository;

import com.jackson.entity.ShopMemberCollectGood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCollectGoodsRepository extends JpaRepository<ShopMemberCollectGood, Long> {
    ShopMemberCollectGood findByMemberIdAndGoodsId(Long memberId, Long goodsId);
}
