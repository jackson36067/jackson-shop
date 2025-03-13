package com.jackson.service;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.GoodsConstant;
import com.jackson.entity.ShopGood;
import com.jackson.repository.GoodsRepository;
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
     * @param type  0.新品 1.热销
     * @param isAll
     * @return
     */
    public Result<List<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll) {
        // 用于暂时存放查询条件,存放到查询条件List中
        Specification<ShopGood> shopGoodSpecification = (root, query, cb) -> {
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
        if (!isAll) {
            // 分页器
            PageRequest pageRequest = PageRequest.of(GoodsConstant.GOODS_PAGE_NUMBER, GoodsConstant.GOODS_PAGE_SIZE, sort);
            Page<ShopGood> shopGoodPage = goodsRepository.findAll(shopGoodSpecification, pageRequest);
            shopGoodList = shopGoodPage.getContent();
        } else {
            shopGoodList = goodsRepository.findAll(shopGoodSpecification, sort);
        }
        List<GoodsMessageVO> goodsMessageVOList = shopGoodList.stream()
                .map(shopGood -> BeanUtil.copyProperties(shopGood, GoodsMessageVO.class))
                .toList();
        return Result.success(goodsMessageVOList);
    }
}
