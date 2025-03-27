package com.jackson.repository;

import com.jackson.entity.ShopMemberBrowseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberBrowseHistoryRepository extends JpaRepository<ShopMemberBrowseHistory, Long> {
    @Query("select s from ShopMemberBrowseHistory s where s.memberId = :memberId and s.type = :type and s.browseTime <= :begin order by s.browseTime desc")
    List<ShopMemberBrowseHistory> findAllByMemberIdAndTypeAndBrowseTimeBefore(@Param("memberId") Long memberId,
                                                                              @Param("type") Short type,
                                                                              @Param("begin") LocalDateTime begin);
}
