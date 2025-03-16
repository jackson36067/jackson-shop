package com.jackson.repository;

import com.jackson.entity.ShopCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<ShopCart, Long> {
    List<ShopCart> findAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update ShopCart s set s.checked = :checked where s.id in :ids")
    void updateAllByIdIn(List<Long> ids,Boolean checked);
}
