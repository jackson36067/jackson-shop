package com.jackson.controller;

import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.service.GoodsService;
import com.jackson.vo.GoodsMessageVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 根据分类id获取商品
     *
     * @param id       分类id
     * @param page     页数
     * @param pageSize 页码
     * @return
     */
    @GetMapping("/category/{id}")
    public Result<GoodsPageResult<GoodsMessageVO>> getGoodsByCategoryId(@PathVariable Long id, Integer page, Integer pageSize){
        return goodsService.getGoodsByCategoryId(id,page,pageSize);
    }
}
