package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.GoodsConstant;
import com.jackson.constant.StoreConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.MemberCollectGoodsDTO;
import com.jackson.entity.ShopGood;
import com.jackson.entity.ShopMemberCollectGood;
import com.jackson.entity.ShopStore;
import com.jackson.repository.GoodsRepository;
import com.jackson.repository.MemberCollectGoodsRepository;
import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.service.GoodsService;
import com.jackson.vo.CollectGoodsVO;
import com.jackson.vo.GoodsMessageVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private MemberCollectGoodsRepository memberCollectGoodsRepository;

    /**
     * 根据条件获取商品
     *
     * @param type      0.新品 1.热销
     * @param isAll     时候获取全部商品
     * @param name      商品名称
     * @param sortType  排序方式 default-根据sort排序 sales-根据销量排序 price-根据价格排序
     * @param orderType 排序类型 0.升序 1.降序
     * @param storeId   店铺id
     * @param page      页码
     * @param pageSize  页码数量
     * @return 商品分页集合
     */
    public Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, String name, String sortType, Integer orderType, Long storeId, Integer page, Integer pageSize) {
        // 封装可变条件 -> 判断条件是获取最新还是热销商品
        Specification<ShopGood> shopGoodSpecification = (root, query, cb) -> {
            // 用于暂时存放查询条件,存放到查询条件List中
            ArrayList<Predicate> predicateList = new ArrayList<>();
            // 判断获取的是新品还是热销产品 -> 封装条件
            if (type == 1) {
                Predicate isHot = cb.equal(root.get(GoodsConstant.IS_HOT), true);
                predicateList.add(isHot);
            } else if (type == 0) {
                Predicate isNew = cb.equal(root.get(GoodsConstant.IS_NEW), true);
                predicateList.add(isNew);
            }
            // 封装商品名称条件
            if (!StringUtil.isNullOrEmpty(name)) {
                Predicate namePredicate = cb.like(root.get(GoodsConstant.NAME), "%" + name + "%");
                predicateList.add(namePredicate);
            }
            // 封装店铺条件
            if (storeId != null) {
                Predicate storeIdCondition = cb.equal(root.get(GoodsConstant.SHOP_STORE).get(StoreConstant.ID), storeId);
                predicateList.add(storeIdCondition);
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            // 将所有条件连接起来
            return cb.and(predicateList.toArray(predicates));
        };
        List<ShopGood> shopGoodList;
        // 判断商品是否还有剩余
        boolean isRemain = true;
        //  通过isAll参数判断是否获取全部商品
        if (!isAll) {
            // 前端首页获取部分商品
            Sort sort = Sort.by(Sort.Direction.ASC, GoodsConstant.SORT_COLUMN);
            // 分页器
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
            Page<ShopGood> shopGoodPage = goodsRepository.findAll(shopGoodSpecification, pageRequest);
            shopGoodList = shopGoodPage.getContent();
        } else {
            // 接收排序结果
            // 二者都存在可以一起排序,二者都没有就按照sort排序
            Sort sort = Sort.by(Sort.Direction.ASC, GoodsConstant.SORT_COLUMN);
            if (orderType != null && orderType == 0) {
                sort = sortType.equals(GoodsConstant.SORT_TYPE_DEFAULT) ?
                        Sort.by(Sort.Direction.ASC, GoodsConstant.SORT_COLUMN)
                        : sortType.equals(GoodsConstant.SORT_TYPE_SALES) ? Sort.by(Sort.Direction.ASC, GoodsConstant.GOODS_SORT_SALE_NUM) : Sort.by(Sort.Direction.ASC, GoodsConstant.GOODS_SORT_PRICE);
            }
            // 排序方式为default-按照sort排序 sales-按照销量排序 price-按照价格排序
            if (orderType != null && orderType == 1) {
                sort = sortType.equals(GoodsConstant.SORT_TYPE_DEFAULT) ?
                        Sort.by(Sort.Direction.DESC, GoodsConstant.SORT_COLUMN)
                        :
                        sortType.equals(GoodsConstant.SORT_TYPE_SALES) ? Sort.by(Sort.Direction.DESC, GoodsConstant.GOODS_SORT_SALE_NUM) : Sort.by(Sort.Direction.DESC, GoodsConstant.GOODS_SORT_PRICE);
            }
            // 点击查看所有商品 -> 做一个下滑获取其余商品的效果
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
            Page<ShopGood> shopGoodPage = goodsRepository.findAll(shopGoodSpecification, pageRequest);
            if (shopGoodPage.getContent().isEmpty()) {
                isRemain = false;
            }
            shopGoodList = shopGoodPage.getContent();
        }
        List<GoodsMessageVO> goodsMessageVOList = shopGoodList.stream()
                .map(shopGood -> BeanUtil.copyProperties(shopGood, GoodsMessageVO.class))
                .toList();
        GoodsPageResult<GoodsMessageVO> goodsMessageVOGoodsPageResult = new GoodsPageResult<>(goodsMessageVOList, isRemain);
        return Result.success(goodsMessageVOGoodsPageResult);
    }

    /**
     * 根据分类id获取商品
     *
     * @param id       分类id
     * @param page     页数
     * @param pageSize 页码
     * @return
     */
    public Result<GoodsPageResult<GoodsMessageVO>> getGoodsByCategoryId(Long id, Integer page, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, GoodsConstant.SORT_COLUMN);
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        boolean isRemain = true;
        Page<ShopGood> shopGoodPage = goodsRepository.findAllByShopCategoryId(id, pageRequest);
        if (shopGoodPage.getContent().isEmpty()) {
            isRemain = false;
        }
        List<ShopGood> shopGoodList = shopGoodPage.getContent();
        List<GoodsMessageVO> goodsMessageVOList = shopGoodList.stream()
                .map(shopGood -> BeanUtil.copyProperties(shopGood, GoodsMessageVO.class))
                .toList();
        GoodsPageResult<GoodsMessageVO> goodsMessageVOGoodsPageResult = new GoodsPageResult<>(goodsMessageVOList, isRemain);
        return Result.success(goodsMessageVOGoodsPageResult);
    }

    /**
     * 用户收藏商品或者取消收藏商品
     *
     * @param memberCollectGoodsDTO
     */
    public void doCollectOrCancelCollectGoods(MemberCollectGoodsDTO memberCollectGoodsDTO) {
        Long userId = BaseContext.getCurrentId();
        Boolean isCollect = memberCollectGoodsDTO.getIsCollect();
        // 判断删除方式 -> 如果idList有值,那么就是删除用户收藏商品信息, 如果是否收藏有值,那么就是单个进行收藏或者取消收藏
        List<Long> idList = memberCollectGoodsDTO.getIdList();
        if (idList != null && !idList.isEmpty()) {
            memberCollectGoodsRepository.deleteAllByIdInBatch(idList);
        }
        // 先判断是否收藏了
        if (isCollect != null && isCollect) {
            // 收藏了 -> 取消收藏
            memberCollectGoodsRepository.deleteByMemberIdAndGoodsId(userId, memberCollectGoodsDTO.getGoodsId());
        } else if (isCollect != null) {
            // 没有收藏
            ShopMemberCollectGood shopMemberCollectGood = new ShopMemberCollectGood(null, userId, memberCollectGoodsDTO.getGoodsId(), memberCollectGoodsDTO.getGoodsName(), null);
            memberCollectGoodsRepository.save(shopMemberCollectGood);
        }
    }

    /**
     * 获取用户收藏商品列表
     *
     * @param name        商品名称
     * @param sortType    排序类型 0.根据收藏时间升序排序 1.根据收藏时间降序排序
     * @param collectTime 获取收藏商品天数范围 比如: 一周前, 一天前, 一个月前
     * @return List<CollectGoodsVO>
     */
    public Result<List<CollectGoodsVO>> getCollectGoodsList(String name, Integer sortType, Integer collectTime) {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            return Result.success(new ArrayList<>());
        }
        Specification<ShopMemberCollectGood> shopMemberCollectGoodSpecification = (root, query, cb) -> {
            // 用于暂时存放查询条件,存放到查询条件List中
            ArrayList<Predicate> predicateList = new ArrayList<>();
            if (collectTime != null) {
                // 根据传递天数获取距离当前时间collectTime的时间
                LocalDateTime pastTime = LocalDateTime.now().minusDays(collectTime);
                // 添加时间范围
                Predicate collectTimeCondition = cb.greaterThan(root.get(GoodsConstant.COLLECT_TIME), pastTime);
                predicateList.add(collectTimeCondition);
            }
            if(!StringUtil.isNullOrEmpty(name)) {
                // 添加商品名称范围
                Predicate nameCondition = cb.like(root.get(GoodsConstant.GOODS_NAME), "%" + name + "%");
                predicateList.add(nameCondition);
            }
            Predicate memberCondition = cb.equal(root.get(GoodsConstant.MEMBER_ID), userId);
            predicateList.add(memberCondition);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return cb.and(predicateList.toArray(predicates));
        };
        List<ShopMemberCollectGood> shopMemberCollectGoodList;
        if (sortType != null) {
            // 判断排序类型
            Sort sort = Sort.by(sortType == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, GoodsConstant.COLLECT_TIME);
            shopMemberCollectGoodList = memberCollectGoodsRepository.findAll(shopMemberCollectGoodSpecification, sort);
        } else {
            shopMemberCollectGoodList = memberCollectGoodsRepository.findAll(shopMemberCollectGoodSpecification);
        }
        List<CollectGoodsVO> collectGoodsVOList = shopMemberCollectGoodList
                .stream()
                .map(shopMemberCollectGood -> {
                    CollectGoodsVO collectGoodsVO = BeanUtil.copyProperties(shopMemberCollectGood, CollectGoodsVO.class);
                    ShopGood shopGood = goodsRepository.findById(shopMemberCollectGood.getGoodsId()).get();
                    collectGoodsVO.setName(shopGood.getName());
                    collectGoodsVO.setPicUrl(shopGood.getPicUrl());
                    collectGoodsVO.setPrice(shopGood.getRetailPrice());
                    ShopStore shopStore = shopGood.getShopStore();
                    collectGoodsVO.setStoreId(shopStore.getId());
                    collectGoodsVO.setStoreName(shopStore.getName());
                    Integer collectNumber = memberCollectGoodsRepository.countByGoodsId(shopMemberCollectGood.getGoodsId());
                    collectGoodsVO.setCollectNumber(collectNumber);
                    return collectGoodsVO;
                })
                .toList();
        return Result.success(collectGoodsVOList);
    }
}
