package com.jackson.repository;

import com.jackson.entity.ShopKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyWordsRepository extends JpaRepository<ShopKeyword,Long> {
    List<ShopKeyword> findAllByIsHotOrderBySortAsc(Boolean isHot);
}
