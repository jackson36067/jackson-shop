package com.jackson.controller;

import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.service.GoodsService;
import com.jackson.vo.GoodsMessageVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 获取热门商品或者新品
     * @param type 0.新品 1.热销
     * @return
     */
    @GetMapping
    public Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, Integer page, Integer pageSize){
        return goodsService.getHotOrNewGoods(type,isAll,page,pageSize);
    }
}
