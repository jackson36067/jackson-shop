package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.ColumnConstant;
import com.jackson.entity.ShopColumn;
import com.jackson.repository.ColumnRepository;
import com.jackson.repository.GoodsRepository;
import com.jackson.result.Result;
import com.jackson.service.ColumnService;
import com.jackson.vo.ColumnDetailVO;
import com.jackson.vo.ColumnHomeVO;
import com.jackson.vo.GoodsMessageVO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnRepository columnRepository;
    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 获取首页栏列表
     *
     * @return
     */
    public Result<List<ColumnHomeVO>> getColumnList() {
        List<ColumnHomeVO> columnHomeVOList = columnRepository
                .findAll(Sort.by(Sort.Direction.ASC, ColumnConstant.ORDER_COLUMN))
                .stream()
                .map(shopColumn -> BeanUtil.copyProperties(shopColumn, ColumnHomeVO.class))
                .toList();
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
