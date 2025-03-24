package com.jackson.service;

import com.jackson.dto.MemberCollectGoodsDTO;
import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.vo.CollectGoodsVO;
import com.jackson.vo.GoodsMessageVO;

import java.util.List;

public interface GoodsService {
    Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, String name, String sortType, Integer orderType, Long storeId, Integer page, Integer pageSize);

    Result<GoodsPageResult<GoodsMessageVO>> getGoodsByCategoryId(Long id, Integer page, Integer pageSize);

    void doCollectOrCancelCollectGoods(MemberCollectGoodsDTO memberCollectGoodsDTO);

    Result<List<CollectGoodsVO>> getCollectGoodsList(String name, Integer sortType, Integer collectTime);
}
