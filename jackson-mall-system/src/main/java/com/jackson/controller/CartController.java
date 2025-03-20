package com.jackson.controller;


import com.jackson.dto.UpdateGoodsCheckedDTO;
import com.jackson.result.Result;
import com.jackson.service.CartService;
import com.jackson.vo.CartGoodsVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 获取用户购物车所有商品
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<CartGoodsVO>> getCartGoodsList() {
        return cartService.getCartGoodsList();
    }

    /**
     * 选中获取取消选中商品
     *
     * @param updateGoodsCheckedDTO
     * @return
     */
    @PutMapping("/update")
    public void doCheckedCartGoods(@RequestBody UpdateGoodsCheckedDTO updateGoodsCheckedDTO) {
        cartService.doCheckedCartGoods(updateGoodsCheckedDTO.getIds(), updateGoodsCheckedDTO.getChecked(), updateGoodsCheckedDTO.getNumber());
    }

    /**
     * 根据id移除购物车中的商品
     * @param id 购物车商品id
     */
    @DeleteMapping("/{id}")
    public void doRemoveGoodsFromCart(@PathVariable Long id) {
        cartService.removeGoodsFromCart(id);
    }
}
