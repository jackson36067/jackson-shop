package com.jackson.repository;

import com.jackson.entity.ShopComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<ShopComment,Long>, JpaSpecificationExecutor<ShopComment> {
    Page<ShopComment> findAllByValueIdAndStar(Long valueId, Short star, Pageable pageable);
    Integer countByValueIdAndStar(Long valueId, Short star);
    Integer countByValueId(Long valueId);
    Integer countByValueIdAndHasPicture(Long valueId, Boolean hasPicture);
}
