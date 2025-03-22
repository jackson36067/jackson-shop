package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.context.BaseContext;
import com.jackson.dto.FollowStoreDTO;
import com.jackson.entity.ShopMemberFollowStore;
import com.jackson.entity.ShopStore;
import com.jackson.repository.MemberFollowStoreRepository;
import com.jackson.repository.StoreRepository;
import com.jackson.result.Result;
import com.jackson.service.StoreService;
import com.jackson.vo.StoreVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreRepository storeRepository;
    @Resource
    private MemberFollowStoreRepository memberFollowStoreRepository;

    /**
     * 根据id获取店家信息
     *
     * @param id 店铺id
     * @return store信息
     */
    public Result<StoreVO> getStoreInfoById(Long id) {
        ShopStore shopStore = storeRepository.findById(id).get();
        StoreVO storeVO = BeanUtil.copyProperties(shopStore, StoreVO.class);
        // 封装店铺信息 -> 粉丝数量
        Integer fansNumber = memberFollowStoreRepository.countByStoreId(id);
        storeVO.setFansNumber(fansNumber);
        // 封装店铺个人信息 -> 是否关注
        Long userId = BaseContext.getCurrentId();
        if (userId != null) {
            Boolean memberFollowStore = memberFollowStoreRepository.existsByMemberIdAndStoreId(userId, id);
            storeVO.setIsFollow(memberFollowStore);
        }
        return Result.success(storeVO);
    }

    /**
     * 用户关注店铺
     *
     * @param followStoreDTO 店铺信息(id,isFollow)
     */
    public void followStore(FollowStoreDTO followStoreDTO) {
        // 判断用户是否已经关注店铺
        if (followStoreDTO.getIsFollow()) {
            // 关注了
            memberFollowStoreRepository.deleteByMemberIdAndStoreId(BaseContext.getCurrentId(), followStoreDTO.getId());
        } else {
            // 没有关注
            ShopMemberFollowStore shopMemberFollowStore = new ShopMemberFollowStore(null, BaseContext.getCurrentId(), followStoreDTO.getId(), null);
            memberFollowStoreRepository.save(shopMemberFollowStore);
        }
    }
}
