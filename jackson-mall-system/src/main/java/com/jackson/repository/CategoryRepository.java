package com.jackson.repository;

import com.jackson.entity.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<ShopCategory, Long> {

    @Query("select c from ShopCategory c where c.pid = :pid and c.delFlag = :isDel order by c.sort asc")
    List<ShopCategory> findAllByPidAndDelFlags(Integer pid, boolean isDel);
}
