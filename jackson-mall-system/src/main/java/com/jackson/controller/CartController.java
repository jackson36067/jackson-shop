package com.jackson.controller;


import com.jackson.dto.CartDTO;
import com.jackson.dto.UpdateGoodsCheckedDTO;
import com.jackson.result.Result;
import com.jackson.service.CartService;
import com.jackson.vo.CartGoodsVO;
import com.jackson.vo.CartSelectGoodsVO;
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

    /**
     * 新增商品至购物车中
     * @param cartDTO 商品信息
     */
    @PostMapping("/add")
    public void addGoodsToCart(@RequestBody CartDTO cartDTO){
        cartService.addGoodsToCart(cartDTO);
    }

    /**
     * 获取用户购物车选中的商品列表
     * @return 购物车选中的商品列表
     */
    @GetMapping("/selected")
    public Result<List<CartSelectGoodsVO>> getSelectedGoodsList() {
        return cartService.getSelectedGoodsList();
    }
}
