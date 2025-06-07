package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aliyuncs.utils.StringUtils;
import com.jackson.constant.BrowseHistoryConstant;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.CancelFollowStoreDTO;
import com.jackson.dto.FollowStoreDTO;
import com.jackson.entity.ShopMemberBrowseHistory;
import com.jackson.entity.ShopMemberFollowStore;
import com.jackson.entity.ShopStore;
import com.jackson.repository.MemberFollowStoreRepository;
import com.jackson.repository.StoreRepository;
import com.jackson.result.Result;
import com.jackson.service.StoreService;
import com.jackson.utils.DateTimeFormatUtils;
import com.jackson.vo.FollowStoreVO;
import com.jackson.vo.StoreVO;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreRepository storeRepository;
    @Resource
    private MemberFollowStoreRepository memberFollowStoreRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;

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
        } else {
            storeVO.setIsFollow(false);
        }
        // 用户没有登录就不用了
        if (userId != null) {
            // 将用户访问店铺添加到浏览记录表中 -> 异步处理,将店铺id以及
            Map<String, Long> browseInfo = new HashMap<>();
            browseInfo.put("storeId", id);
            browseInfo.put("memberId", userId);
            browseInfo.put("type", 1L);
            rabbitTemplate.convertAndSend(RabbitMQConstant.BROWSE_QUEUE_KEY, browseInfo);
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

    /**
     * 获取用户关注店铺列表
     *
     * @param name 店铺名称
     * @return 用户关注店铺列表
     */
    public Result<List<FollowStoreVO>> getFollowStoreList(String name) {
        List<ShopMemberFollowStore> shopMemberFollowStoreList = memberFollowStoreRepository.findAllByMemberId(BaseContext.getCurrentId());
        List<FollowStoreVO> followStoreVOList = shopMemberFollowStoreList.stream()
                .map(shopMemberFollowStore -> {
                    ShopStore shopStore = storeRepository.findById(shopMemberFollowStore.getStoreId()).get();
                    FollowStoreVO followStoreVO = BeanUtil.copyProperties(shopStore, FollowStoreVO.class);
                    // id需要改过来
                    followStoreVO.setId(shopMemberFollowStore.getId());
                    followStoreVO.setStoreId(shopStore.getId());
                    followStoreVO.setFollowTime(DateTimeFormatUtils.getTimeAgo(shopMemberFollowStore.getCreateTime()));
                    return followStoreVO;
                })
                .toList();
        // 判断是否有带店铺名称参数 -> 带了根据店铺名称过滤
        if (!StringUtils.isEmpty(name)) {
            followStoreVOList = followStoreVOList
                    .stream()
                    .filter(followStoreVO -> followStoreVO.getName().contains(name))
                    .toList();
        }
        return Result.success(followStoreVOList);
    }

    /**
     * 取消关注店铺
     *
     * @param cancelFollowStoreDTO 取消关注店铺id集合
     */
    public void cancelFollowStore(CancelFollowStoreDTO cancelFollowStoreDTO) {
        memberFollowStoreRepository.deleteAllByIdInBatch(cancelFollowStoreDTO.getIdList());
    }
}
