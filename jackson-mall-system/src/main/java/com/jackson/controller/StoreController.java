package com.jackson.controller;

import com.jackson.dto.FollowStoreDTO;
import com.jackson.result.Result;
import com.jackson.service.StoreService;
import com.jackson.vo.StoreVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
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
}
