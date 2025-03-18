package com.jackson.repository;

import com.jackson.entity.ShopSearchHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<ShopSearchHistory,Long> {

    /**
     * 根据用户id获取用户历史搜索记录,根据创建时间降序排序
     * @param userId
     * @return
     */
    @Query("select s from ShopSearchHistory s where s.userId = :userId order by s.createTime desc")
    List<ShopSearchHistory> findAllByUserId(Long userId, Pageable pageable);

    @Transactional
    void deleteAllByUserId(Long userId);

    ShopSearchHistory findByKeywordAndUserId(String keyword,Long userId);
}
