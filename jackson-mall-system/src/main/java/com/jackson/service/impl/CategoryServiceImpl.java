package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.entity.ShopCategory;
import com.jackson.repository.CategoryRepository;
import com.jackson.result.Result;
import com.jackson.service.CategoryService;
import com.jackson.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取分类列表集合接口
     *
     * @return
     */
    public Result<List<CategoryVO>> getCategoryList() {
        String categoryJsonList = stringRedisTemplate.opsForValue().get(RedisConstant.SHOP_CATEGORY_KEY);
        List<CategoryVO> categoryVOList = List.of();
        if (categoryJsonList == null || categoryJsonList.isEmpty()) {
            List<ShopCategory> shopCategoryList = categoryRepository.findAllByPidAndDelFlags(0, false);
            categoryVOList = shopCategoryList.stream()
                    .map(shopCategory -> {
                        CategoryVO categoryVO = BeanUtil.copyProperties(shopCategory, CategoryVO.class);
                        List<CategoryVO> subShopCategoryList = categoryRepository.findAllByPidAndDelFlags(Integer.parseInt(shopCategory.getId().toString()), false)
                                .stream()
                                .map(subCategory -> BeanUtil.copyProperties(subCategory, CategoryVO.class))
                                .toList();
                        categoryVO.setSubCategoryList(subShopCategoryList);
                        return categoryVO;
                    }).toList();
            // 将分类信息保存到Redis中, 设置过期时间为两天
            stringRedisTemplate.opsForValue().set(RedisConstant.SHOP_CATEGORY_KEY, JSONUtil.toJsonStr(categoryVOList), RedisConstant.SHOP_CATEGORY_EXPIRE_TIME, TimeUnit.DAYS);
        } else {
            categoryVOList = JSONUtil.toList(categoryJsonList, CategoryVO.class);
        }
        return Result.success(categoryVOList);
    }
}
