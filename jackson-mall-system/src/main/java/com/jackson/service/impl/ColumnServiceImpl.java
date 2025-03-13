package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.ColumnConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.entity.ShopColumn;
import com.jackson.repository.ColumnRepository;
import com.jackson.repository.GoodsRepository;
import com.jackson.result.Result;
import com.jackson.service.ColumnService;
import com.jackson.vo.ColumnDetailVO;
import com.jackson.vo.ColumnHomeVO;
import com.jackson.vo.GoodsMessageVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnRepository columnRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取首页栏列表
     *
     * @return
     */
    public Result<List<ColumnHomeVO>> getColumnList() {
        // 从Redis中获取栏目数据
        String shopColumnJsonList = stringRedisTemplate.opsForValue().get(RedisConstant.SHOP_COLUMN_KEY);
        List<ColumnHomeVO> columnHomeVOList = List.of();
        // 判断Redis中是否有数据
        if (StringUtil.isNullOrEmpty(shopColumnJsonList)) {
            // 没有 -> 从数据库中获取栏目数据
            columnHomeVOList = columnRepository
                    .findAll(Sort.by(Sort.Direction.ASC, ColumnConstant.ORDER_COLUMN))
                    .stream()
                    .map(shopColumn -> BeanUtil.copyProperties(shopColumn, ColumnHomeVO.class))
                    .toList();
            // 将栏目数据保存到Redis中
            stringRedisTemplate.opsForValue().set(RedisConstant.SHOP_COLUMN_KEY, JSONUtil.toJsonStr(columnHomeVOList), RedisConstant.SHOP_COLUMN_EXPIRE_TIME, TimeUnit.DAYS);
        } else {
            // 有 -> 直接从Redis中获取数据
            columnHomeVOList = JSONUtil.toList(shopColumnJsonList,ColumnHomeVO.class);
        }
        return Result.success(columnHomeVOList);
    }


    /**
     * 通过id获取栏目详情信息
     *
     * @param id 栏目id
     * @return 包含栏目id, 名称以及其中的商品信息
     */
    public Result<ColumnDetailVO> getColumnDetailById(Long id) {
        ShopColumn shopColumn = columnRepository.findById(id).get();
        ColumnDetailVO columnDetailVO = BeanUtil.copyProperties(shopColumn, ColumnDetailVO.class);
        List<GoodsMessageVO> goodsMessageVOList = shopColumn.getShopGood()
                .stream()
                .map(shopGood -> {
                    return BeanUtil.copyProperties(shopGood, GoodsMessageVO.class);
                })
                .toList();
        columnDetailVO.setGoodsMessageVOList(goodsMessageVOList);

        return Result.success(columnDetailVO);
    }
}
