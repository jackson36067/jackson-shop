package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.BrowseHistoryConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.AddBrowseSearchHistoryDTO;
import com.jackson.dto.RemoveBrowseHistoryDTO;
import com.jackson.entity.ShopGood;
import com.jackson.entity.ShopMemberBrowseHistory;
import com.jackson.entity.ShopStore;
import com.jackson.repository.GoodsRepository;
import com.jackson.repository.MemberBrowseHistoryRepository;
import com.jackson.repository.MemberFollowStoreRepository;
import com.jackson.repository.StoreRepository;
import com.jackson.result.Result;
import com.jackson.service.MemberBrowseHistoryService;
import com.jackson.utils.DateTimeFormatUtils;
import com.jackson.vo.GoodsHistoryVO;
import com.jackson.vo.MemberBrowseHistoryVO;
import com.jackson.vo.StoreHistoryVO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MemberBrowseHistoryServiceImpl implements MemberBrowseHistoryService {
    @Resource
    private MemberBrowseHistoryRepository memberBrowseHistoryRepository;
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private StoreRepository storeRepository;
    @Resource
    private MemberFollowStoreRepository memberFollowStoreRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取用户历史浏览记录
     *
     * @param type      浏览记录类型 0.商品 1.店铺 2.评论
     * @param goodsName 商品名称
     * @param begin     时间筛选
     * @return MemberBrowseHistoryVO
     */
    public Result<List<MemberBrowseHistoryVO>> getMemberBrowseHistoryList(Short type, String goodsName, LocalDate begin) {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            return Result.success(new ArrayList<>());
        }
        // 根据用户id以及获取历史记录类型获取用户的历史记录
        List<ShopMemberBrowseHistory> shopMemberBrowseHistoryList = memberBrowseHistoryRepository.findAllByMemberIdAndTypeAndBrowseTimeBefore(userId, type, LocalDateTime.now());
        // 判断是否有带时间筛选
        if (begin != null) {
            LocalDateTime from = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 23, 59, 59);
            shopMemberBrowseHistoryList = memberBrowseHistoryRepository.findAllByMemberIdAndTypeAndBrowseTimeBefore(userId, type, from);
        }
        // 返回数据对象
        List<MemberBrowseHistoryVO> memberBrowseHistoryVOList = List.of();
        // 通过浏览日期分类
        Map<LocalDate, List<ShopMemberBrowseHistory>> collect =
                shopMemberBrowseHistoryList.stream().collect(Collectors.groupingBy(shopMemberBrowseHistory -> shopMemberBrowseHistory.getBrowseTime().toLocalDate()));
        // 获取商品历史浏览记录
        if (type == 0) {
            memberBrowseHistoryVOList = collect.keySet().stream().map(key -> {
                        MemberBrowseHistoryVO memberBrowseHistoryVO = new MemberBrowseHistoryVO();
                        // 设置浏览日期
                        memberBrowseHistoryVO.setBrowseTime(DateTimeFormatUtils.formatDate(key));
                        memberBrowseHistoryVO.setBrowseDate(key);
                        // 设置浏览内容
                        List<GoodsHistoryVO> goodsHistoryVOList = collect.get(key).stream().map(shopMemberBrowseHistory -> {
                                    ShopGood shopGood = goodsRepository.findById(shopMemberBrowseHistory.getGoodsId()).get();
                                    GoodsHistoryVO goodsHistoryVO = BeanUtil.copyProperties(shopGood, GoodsHistoryVO.class);
                                    goodsHistoryVO.setGoodsName(shopGood.getName());
                                    goodsHistoryVO.setPrice(shopGood.getRetailPrice());
                                    goodsHistoryVO.setBrowseId(shopMemberBrowseHistory.getId());
                                    return goodsHistoryVO;
                                })
                                .toList();
                        // 如果有通过商品名称搜索就过滤出该名称的商品数据
                        if (goodsName != null && !goodsName.isBlank()) {
                            goodsHistoryVOList = goodsHistoryVOList
                                    .stream()
                                    .filter(goodsHistoryVO -> goodsHistoryVO.getGoodsName().contains(goodsName))
                                    .toList();
                        }
                        memberBrowseHistoryVO.setData(goodsHistoryVOList);
                        return memberBrowseHistoryVO;
                    })
                    .toList();
        }
        // 获取店铺历史浏览记录
        if (type == 1) {
            memberBrowseHistoryVOList = collect.keySet()
                    .stream()
                    .map(
                            key -> {
                                MemberBrowseHistoryVO memberBrowseHistoryVO = new MemberBrowseHistoryVO();
                                // 设置浏览日期
                                memberBrowseHistoryVO.setBrowseTime(DateTimeFormatUtils.formatDate(key));
                                memberBrowseHistoryVO.setBrowseDate(key);
                                // 设置浏览内容
                                List<StoreHistoryVO> storeHistoryVOList = collect.get(key).stream().map(shopMemberBrowseHistory -> {
                                    ShopStore shopStore = storeRepository.findById(shopMemberBrowseHistory.getStoreId()).get();
                                    StoreHistoryVO storeHistoryVO = new StoreHistoryVO();
                                    storeHistoryVO.setId(shopStore.getId());
                                    storeHistoryVO.setStoreName(shopStore.getName());
                                    storeHistoryVO.setAvatar(shopStore.getAvatar());
                                    storeHistoryVO.setFollowNumber(memberFollowStoreRepository.countByStoreId(shopStore.getId()));
                                    storeHistoryVO.setBrowseId(shopMemberBrowseHistory.getId());
                                    List<GoodsHistoryVO> goodsHistoryVOList = goodsRepository.findAllByShopStore_Id(shopStore.getId(), PageRequest.of(0, 3)).getContent()
                                            .stream()
                                            .map(shopGood -> {
                                                GoodsHistoryVO goodsHistoryVO = BeanUtil.copyProperties(shopGood, GoodsHistoryVO.class);
                                                goodsHistoryVO.setGoodsName(shopGood.getName());
                                                goodsHistoryVO.setPrice(shopGood.getRetailPrice());
                                                return goodsHistoryVO;
                                            })
                                            .toList();
                                    storeHistoryVO.setGoodsHistoryVOList(goodsHistoryVOList);
                                    return storeHistoryVO;
                                }).toList();
                                memberBrowseHistoryVO.setData(storeHistoryVOList);
                                return memberBrowseHistoryVO;
                            })
                    .toList();
        }
        memberBrowseHistoryVOList = memberBrowseHistoryVOList.stream()
                .sorted((a, b) -> b.getBrowseDate().compareTo(a.getBrowseDate()))
                .collect(Collectors.toCollection(ArrayList::new));
        return Result.success(memberBrowseHistoryVOList);
    }

    /**
     * 删除用户历史浏览记录
     *
     * @param removeBrowseHistoryDTO 用户历史浏览记录id
     */
    public void removeMemberBrowseHistory(RemoveBrowseHistoryDTO removeBrowseHistoryDTO) {
        memberBrowseHistoryRepository.deleteAllByIdInBatch(removeBrowseHistoryDTO.getIdList());
    }

    /**
     * 通过redis保存用户的浏览搜索历史记录, 设置过期时间为7天
     *
     * @param addBrowseSearchHistoryDTO 搜索名称
     */
    public void addBrowseSearchInfo(AddBrowseSearchHistoryDTO addBrowseSearchHistoryDTO) {
        Long memberId = BaseContext.getCurrentId();
        String date = LocalDate.now().toString();
        // 以用户id以及当天日期作为key保存用户浏览记录搜索记录
        Boolean add = stringRedisTemplate.opsForZSet().add(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + memberId + ":" + date, addBrowseSearchHistoryDTO.getValue(), System.currentTimeMillis());
        stringRedisTemplate.expire(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + date, BrowseHistoryConstant.BROWSE_SEARCH_HISTORY_EXPIRE_TIME, TimeUnit.DAYS);
    }

    /**
     * 获取用户浏览记录搜索列表
     *
     * @return 用户搜索记录
     */
    public Result<List<String>> getBrowseSearchHistoryList() {
        Long memberId = BaseContext.getCurrentId();
        List<String> list = new ArrayList<>();
        // 通过匹配key的方式获取用户的所有浏览记录搜索记录
        Set<String> keys = stringRedisTemplate.keys(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + memberId + ":" + "*");
        if (!keys.isEmpty()) {
            // 获取key最后的日期进行排序,日期越大的在前
            List<String> valueList = keys.stream().sorted((o1, o2) -> {
                String[] split1 = o1.split(":");
                String[] split2 = o2.split(":");
                return split2[split2.length - 1].compareTo(split1[split1.length - 1]);
            }).toList();
            valueList.forEach(key -> {
                Set<String> strings = stringRedisTemplate.opsForZSet().reverseRange(key, 0, -1);
                if (strings != null && !strings.isEmpty()) {
                    // 判断该值是否存在,存在了就不需要加入了
                    strings.forEach(string -> {
                        if (!list.contains(string)) {
                            list.add(string);
                        }
                    });
                }
            });
        }
        return Result.success(list);
    }

    /**
     * 移除用户浏览记录搜索列表
     */
    public void removeAllBrowseSearchHistory() {
        Long memberId = BaseContext.getCurrentId();
        Set<String> keys = stringRedisTemplate.keys(BrowseHistoryConstant.BROWSE_SEARCH_KEY_PREFIX + memberId + ":" + "*");
        if(!keys.isEmpty()) {
            stringRedisTemplate.delete(keys);
        }
    }
}
