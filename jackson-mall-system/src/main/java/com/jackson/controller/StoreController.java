package com.jackson.controller;

import com.jackson.dto.CancelFollowStoreDTO;
import com.jackson.dto.FollowStoreDTO;
import com.jackson.result.Result;
import com.jackson.service.StoreService;
import com.jackson.vo.FollowStoreVO;
import com.jackson.vo.StoreVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    /**
     * 根据id获取店家信息
     *
     * @param id 店铺id
     * @return store信息
     */
    @GetMapping("/info/{id}")
    public Result<StoreVO> getStoreInfoById(@PathVariable Long id) {
        return storeService.getStoreInfoById(id);
    }

    /**
     * 用户关注店铺
     *
     * @param followStoreDTO 店铺信息(id,isFollow)
     */
    @PostMapping("/follow")
    public void followStore(@RequestBody FollowStoreDTO followStoreDTO) {
        storeService.followStore(followStoreDTO);
    }

    /**
     * 获取用户关注店铺列表
     *
     * @return 用户关注店铺列表
     */
    @GetMapping("/follow/list")
    public Result<List<FollowStoreVO>> getFollowStoreList(@RequestParam(required = false) String name) {
        return storeService.getFollowStoreList(name);
    }

    /**
     * 取消关注店铺
     *
     * @param cancelFollowStoreDTO 取消关注店铺id集合
     */
    @DeleteMapping("/cancel/follow")
    public void cancelFollowStore(@RequestBody CancelFollowStoreDTO cancelFollowStoreDTO) {
        storeService.cancelFollowStore(cancelFollowStoreDTO);
    }
}
