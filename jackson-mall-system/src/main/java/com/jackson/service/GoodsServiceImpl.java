package com.jackson.service;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.GoodsConstant;
import com.jackson.entity.ShopGood;
import com.jackson.repository.GoodsRepository;
import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.vo.GoodsMessageVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 获取热门商品或者新品
     *
     * @param type     0.新品 1.热销
     * @param isAll
     * @param page
     * @param pageSize
     * @return
     */
    public Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, Integer page, Integer pageSize) {
        // 封装可变条件 -> 判断条件是获取最新还是热销商品
        Specification<ShopGood> shopGoodSpecification = (root, query, cb) -> {
            // 用于暂时存放查询条件,存放到查询条件List中
            ArrayList<Predicate> predicateList = new ArrayList<>();
            if (type == 1) {
                Predicate isHot = cb.equal(root.get(GoodsConstant.IS_HOT), true);
                predicateList.add(isHot);
            } else if (type == 0) {
                Predicate isNew = cb.equal(root.get(GoodsConstant.IS_NEW), true);
                predicateList.add(isNew);
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            // 将所有条件连接起来
            return cb.and(predicateList.toArray(predicates));
        };
        Sort sort = Sort.by(Sort.Direction.ASC, GoodsConstant.SORT_COLUMN);
        List<ShopGood> shopGoodList;
        //  通过isAll参数判断是否获取全部商品
        boolean isRemain = true;
        if (!isAll) {
            // 前端首页获取部分商品
            // 分页器
            PageRequest pageRequest = PageRequest.of(GoodsConstant.GOODS_PAGE_NUMBER, GoodsConstant.GOODS_PAGE_SIZE, sort);
            Page<ShopGood> shopGoodPage = goodsRepository.findAll(shopGoodSpecification, pageRequest);
            shopGoodList = shopGoodPage.getContent();
        } else {
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
}
