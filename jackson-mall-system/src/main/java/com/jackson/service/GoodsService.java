package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.GoodsMessageVO;

import java.util.List;

public interface GoodsService {
    Result<List<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll);
}
