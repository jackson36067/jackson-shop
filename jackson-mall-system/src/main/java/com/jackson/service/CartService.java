package com.jackson.service;

import com.jackson.dto.CartDTO;
import com.jackson.result.Result;
import com.jackson.vo.CartGoodsVO;
import com.jackson.vo.CartSelectGoodsVO;

import java.util.List;

public interface CartService {
    Result<List<CartGoodsVO>> getCartGoodsList();

    void doCheckedCartGoods(List<Long> ids, Boolean checked, Short number);

    void removeGoodsFromCart(Long id);

    void addGoodsToCart(CartDTO cartDTO);

    Result<List<CartSelectGoodsVO>> getSelectedGoodsList();
}
