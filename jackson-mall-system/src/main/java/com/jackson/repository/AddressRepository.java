package com.jackson.repository;

import com.jackson.entity.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<ShopAddress, Long> {
        ShopAddress findByIsDefaultAndMemberId(short isDefault,Long memberId);
        List<ShopAddress> findAllByMemberId(Long memberId);
}
