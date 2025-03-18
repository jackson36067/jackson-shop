package com.jackson.service;

import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.vo.GoodsMessageVO;

public interface GoodsService {
    Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, String name, String sortType, Integer orderType, Integer page, Integer pageSize);

    Result<GoodsPageResult<GoodsMessageVO>> getGoodsByCategoryId(Long id, Integer page, Integer pageSize);
}
