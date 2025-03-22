package com.jackson.controller;

import com.jackson.dto.MemberCollectGoodsDTO;
import com.jackson.result.GoodsPageResult;
import com.jackson.result.Result;
import com.jackson.service.GoodsService;
import com.jackson.vo.GoodsMessageVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 根据条件获取商品
     *
     * @param type      0.新品 1.热销
     * @param isAll     时候获取全部商品
     * @param name      商品名称
     * @param sortType  排序方式 default-根据sort排序 sales-根据销量排序 price-根据价格排序
     * @param orderType 排序类型 0.升序 1.降序
     * @param storeId   店铺id
     * @param page      页码
     * @param pageSize  页码数量
     * @return 商品分页集合
     */
    @GetMapping
    public Result<GoodsPageResult<GoodsMessageVO>> getHotOrNewGoods(Integer type, Boolean isAll, @RequestParam(required = false) String name, @RequestParam(required = false) String sortType, @RequestParam(required = false) Integer orderType, @RequestParam(required = false) Long storeId, Integer page, Integer pageSize) {
        return goodsService.getHotOrNewGoods(type, isAll, name, sortType, orderType, storeId, page, pageSize);
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

    /**
     * 用户收藏商品或者取消收藏商品
     *
     * @param memberCollectGoodsDTO
     */
    @PostMapping("/collect")
    public void doCollectOrCancelCollectGoods(@RequestBody MemberCollectGoodsDTO memberCollectGoodsDTO) {
        goodsService.doCollectOrCancelCollectGoods(memberCollectGoodsDTO);
    }
}
