package com.jackson.repository;

import com.jackson.entity.ShopSearchHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<ShopSearchHistory,Long> {

    List<ShopSearchHistory> findAllByUserId(Long userId);

    @Transactional
    void deleteAllByUserId(Long userId);
}
