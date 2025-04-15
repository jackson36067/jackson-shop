package com.jackson.controller;

import com.jackson.dto.RemoveMemberCouponDTO;
import com.jackson.dto.StoreCouponDTO;
import com.jackson.result.Result;
import com.jackson.service.CouponService;
import com.jackson.vo.CouponVO;
import com.jackson.vo.MemberCouponItemVO;
import com.jackson.vo.MemberCouponTypeVO;
import com.jackson.vo.MemberCouponVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    /**
     * 获取店铺提供的优惠卷
     *
     * @param id 店家id
     * @return
     */
    @GetMapping("/store/{id}")
    public Result<List<CouponVO>> getUnGetStoreCouponList(@PathVariable Long id) {
        return couponService.getUnGetStoreCouponList(id);
    }

    /**
     * 领取店铺提供的优惠卷
     *
     * @param storeCouponDTO 店铺id以及优惠卷id
     */
    @PostMapping("/get")
    public void getStoreCoupon(@RequestBody StoreCouponDTO storeCouponDTO) {
        couponService.getStoreCoupon(storeCouponDTO);
    }

    /**
     * 获取用户尚未使用的优惠卷
     *
     * @return
     */
    @GetMapping("/member")
    public Result<List<MemberCouponTypeVO>> getMemberCouponList() {
        return couponService.getMemberCouponList();
    }

    /**
     * 根据用户优惠卷id集合,珊瑚用户优惠卷,支持多删
     *
     * @param removeMemberCouponDTO 用户优惠卷id
     */
    @PutMapping("/remove")
    public void removeMemberCoupon(@RequestBody RemoveMemberCouponDTO removeMemberCouponDTO) {
        couponService.removeAllByMemberCouponIdList(removeMemberCouponDTO);
    }

    /**
     * 获取用户可领取的平台提供的优惠卷
     *
     * @return
     */
    @GetMapping("/center")
    public Result<List<CouponVO>> getCenterCoupon() {
        return couponService.getCenterCoupon();
    }

    /**
     * 获取店铺可用优惠卷以及可用平台卷
     * @param id 店铺id
     * @return
     */
    @GetMapping("/use")
    public Result<List<CouponVO>> getMemberCanUseCoupon(@RequestParam List<Long> storeIds) {
        return couponService.getMemberCanUseCoupon(storeIds);
    }
}
