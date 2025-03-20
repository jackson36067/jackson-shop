package com.jackson.service;

import com.jackson.dto.StoreCouponDTO;
import com.jackson.result.Result;
import com.jackson.vo.CouponVO;

import java.util.List;

public interface CouponService {
    Result<List<CouponVO>> getUnGetStoreCouponList(Long id);

    void getStoreCoupon(StoreCouponDTO shopCouponDTO);
}
