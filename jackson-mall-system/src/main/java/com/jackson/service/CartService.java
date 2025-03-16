package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.CartGoodsVO;

import java.util.List;

public interface CartService {
    Result<List<CartGoodsVO>> getCartGoodsList();

    void doCheckedCartGoods(List<Long> ids, Boolean checked, Short number);
}
