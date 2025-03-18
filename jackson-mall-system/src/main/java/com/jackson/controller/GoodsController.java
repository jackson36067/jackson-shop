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

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 根据条件获取商品
     *
     * @param type            0.新品 1.热销
     * @param isAll           时候获取全部商品
     * @param name            商品名称
     * @param sortType        排序方式 default-根据sort排序 sales-根据销量排序 price-根据价格排序
     * @param orderType       排序类型 0.升序 1.降序
     * @param page            页码
     * @param pageSize        页码数量
     * @return 商品分页集合
     */
    @GetMapping
    public Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, String name, String sortType, Integer orderType, Integer page, Integer pageSize) {
        return goodsService.getHotOrNewGoods(type, isAll, name, sortType, orderType, page, pageSize);
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
    public Result<GoodsPageResult<GoodsMessageVO>> getGoodsByCategoryId(@PathVariable Long id, Integer page, Integer pageSize) {
        return goodsService.getGoodsByCategoryId(id, page, pageSize);
    }
}
