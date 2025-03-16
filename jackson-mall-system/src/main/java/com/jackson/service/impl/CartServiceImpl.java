package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.context.BaseContext;
import com.jackson.entity.ShopCart;
import com.jackson.entity.ShopCategory;
import com.jackson.repository.CartRepository;
import com.jackson.repository.CategoryRepository;
import com.jackson.result.Result;
import com.jackson.service.CartService;
import com.jackson.vo.CartGoodsVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;

    /**
     * 获取用户购物车所有商品
     *
     * @return
     */
    public Result<List<CartGoodsVO>> getCartGoodsList() {
        Long userId = BaseContext.getCurrentId();
        List<ShopCart> shopCartList = cartRepository.findAllByUserId(userId);
        List<CartGoodsVO> cartGoodsVOList = shopCartList.stream()
                .map(shopCart ->
                        {
                            CartGoodsVO cartGoodsVO = BeanUtil.copyProperties(shopCart, CartGoodsVO.class);
                            List<String> specificationsList = JSONUtil.toList(shopCart.getSpecifications(), String.class);
                            String specification = String.join(" ", specificationsList);
                            cartGoodsVO.setSpecifications(specification);
                            return cartGoodsVO;
                        }
                )
                .toList();
        return Result.success(cartGoodsVOList);
    }

    /**
     * 选中获取取消选中商品
     *
     * @param ids     购物车商品id,可以是多个
     * @param checked 是否选中 可传递可不传递
     * @param number 商品数量 可传递可不传递
     * @return
     */
    public void doCheckedCartGoods(List<Long> ids, Boolean checked, Short number) {

        // 判断修改商品的选中状态还是数量
        if (checked != null) {
            // 修改选中状态 -> 可能有多个商品
            cartRepository.updateAllByIdIn(ids, checked);
        }

        if (number != null) {
            // 修改商品数量 -> 一次只能有一个商品
            ShopCart shopCart = cartRepository.findById(ids.get(0)).get();
            shopCart.setNumber(number);
            cartRepository.saveAndFlush(shopCart);
        }
    }
}
