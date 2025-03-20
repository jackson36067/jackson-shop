package com.jackson.repository;

import com.jackson.entity.ShopMemberCollectGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemberCollectGoodsRepository extends JpaRepository<ShopMemberCollectGood, Long> {
    ShopMemberCollectGood findByMemberIdAndGoodsId(Long memberId, Long goodsId);

    @Modifying
    @Transactional
    @Query("delete from ShopMemberCollectGood s where s.memberId = :memberId and s.goodsId = :goodsId")
    void deleteByMemberIdAndGoodsId(Long memberId,Long goodsId);
}
