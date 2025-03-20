package com.jackson.controller;

import com.jackson.dto.StoreCouponDTO;
import com.jackson.result.Result;
import com.jackson.service.CouponService;
import com.jackson.vo.CouponVO;
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
     * @param storeCouponDTO 店铺id以及优惠卷id
     */
    @PostMapping("/get")
    public void getStoreCoupon(@RequestBody StoreCouponDTO storeCouponDTO) {
        couponService.getStoreCoupon(storeCouponDTO);
    }

}
