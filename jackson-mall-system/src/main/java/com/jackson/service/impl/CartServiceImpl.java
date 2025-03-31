package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.context.BaseContext;
import com.jackson.entity.*;
import com.jackson.repository.*;
import com.jackson.result.Result;
import com.jackson.service.CartService;
import com.jackson.vo.CartGoodsVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private MemberCollectGoodsRepository memberCollectGoodsRepository;
    @Resource
    private MemberCouponRepository memberCouponRepository;
    @Autowired
    private MemberFollowStoreRepository memberFollowStoreRepository;

    /**
     * 获取用户购物车所有商品
     *
     * @return
     */
    public Result<List<CartGoodsVO>> getCartGoodsList() {
        Long userId = BaseContext.getCurrentId();
        // 防止没有登录时获取购物车报错
        if (userId == null) {
            return Result.success();
        }
        List<ShopCart> shopCartList = cartRepository.findAllByUserId(userId);
        List<CartGoodsVO> cartGoodsVOList = shopCartList.stream()
                .map(shopCart ->
                        {
                            // shopCart中冗余了一些商品信息,将信息转换成需要的类型
                            CartGoodsVO cartGoodsVO = BeanUtil.copyProperties(shopCart, CartGoodsVO.class);
                            // 处理商品规格列表
                            List<String> specificationsList = JSONUtil.toList(shopCart.getSpecifications(), String.class);
                            String specification = String.join(" ", specificationsList);
                            cartGoodsVO.setSpecifications(specification);
                            // 获取商品的店家
                            ShopGood shopGood = goodsRepository.findById(shopCart.getGoodsId()).get();
                            ShopStore shopStore = shopGood.getShopStore();
                            cartGoodsVO.setStoreId(shopStore.getId());
                            cartGoodsVO.setStoreName(shopStore.getName());
                            cartGoodsVO.setIsFollow(memberFollowStoreRepository.existsByMemberIdAndStoreId(userId,shopStore.getId()));
                            // 判断店家是否提供优惠卷 -> 从优惠卷数据库中判断该店家是否有提供优惠卷以及这些优惠卷用户是否已经领取
                            List<ShopCoupon> shopCouponList = couponRepository.findAllByShopStore_IdAndExpireTimeAfter(shopGood.getShopStore().getId(), LocalDateTime.now());
                            // 用户是否有可领取的优惠卷
                            AtomicBoolean isUserGet = new AtomicBoolean(false);
                            // 判断用户是否有领取优惠卷 -> 只要有一个没有领取就设置为true
                            for (ShopCoupon shopCoupon : shopCouponList) {
                                ShopMemberCoupon shopMemberCoupon = memberCouponRepository.findByUserIdAndCouponId(userId, shopCoupon.getId());
                                if (shopMemberCoupon == null) {
                                    isUserGet.set(true);
                                    break;
                                }
                            }
                            cartGoodsVO.setIsContainCoupon(!shopCouponList.isEmpty() && isUserGet.get());
                            // 判断用户是否收藏了商品
                            cartGoodsVO.setIsCollect(memberCollectGoodsRepository.findByMemberIdAndGoodsId(userId, shopCart.getGoodsId()) != null);
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
     * @param number  商品数量 可传递可不传递
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

    /**
     * 根据id移除购物车中的商品
     *
     * @param id 购物车商品id
     */
    public void removeGoodsFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}
