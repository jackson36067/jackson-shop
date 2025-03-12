package com.jackson.repository;

import com.jackson.entity.ShopColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<ShopColumn, Long> {
}
