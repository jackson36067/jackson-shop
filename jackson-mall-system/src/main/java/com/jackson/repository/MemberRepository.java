package com.jackson.repository;

import com.jackson.entity.ShopMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<ShopMember, Long> {
    ShopMember findByNickname(String nickname);
    ShopMember findByEmail(String email);
}
