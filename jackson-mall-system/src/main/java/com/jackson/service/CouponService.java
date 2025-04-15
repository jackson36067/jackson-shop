package com.jackson.service;

import com.jackson.dto.RemoveMemberCouponDTO;
import com.jackson.dto.StoreCouponDTO;
import com.jackson.result.Result;
import com.jackson.vo.CouponVO;
import com.jackson.vo.MemberCouponTypeVO;

import java.util.List;

public interface CouponService {
    Result<List<CouponVO>> getUnGetStoreCouponList(Long id);

    void getStoreCoupon(StoreCouponDTO shopCouponDTO);

    Result<List<MemberCouponTypeVO>> getMemberCouponList();

    void removeAllByMemberCouponIdList(RemoveMemberCouponDTO removeMemberCouponDTO);

    Result<List<CouponVO>> getCenterCoupon();

    Result<List<CouponVO>> getMemberCanUseCoupon(List<Long> storeIds);
}
