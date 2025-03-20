package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.GoodsConstant;
import com.jackson.entity.ShopGood;
import com.jackson.repository.GoodsRepository;
import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.service.GoodsService;
import com.jackson.vo.GoodsMessageVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 根据条件获取商品
     *
     * @param type      0.新品 1.热销
     * @param isAll     时候获取全部商品
     * @param name      商品名称
     * @param sortType  排序方式 default-根据sort排序 sales-根据销量排序 price-根据价格排序
     * @param orderType 排序类型 0.升序 1.降序
     * @param page      页码
     * @param pageSize  页码数量
     * @return 商品分页集合
     */
    public Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, String name, String sortType, Integer orderType, Integer page, Integer pageSize) {
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
            if (!StringUtil.isNullOrEmpty(name)) {
                Predicate namePredicate = cb.like(root.get(GoodsConstant.NAME), "%" + name + "%");
                predicateList.add(namePredicate);
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
            if (orderType != null &&  orderType == 1) {
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
}
