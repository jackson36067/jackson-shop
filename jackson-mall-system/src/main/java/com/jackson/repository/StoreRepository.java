package com.jackson.repository;

import com.jackson.entity.ShopStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<ShopStore,Long> {
}
