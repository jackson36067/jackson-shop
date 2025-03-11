package com.jackson.controller;

import com.jackson.result.Result;
import com.jackson.service.CategoryService;
import com.jackson.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取分类列表集合接口
     * @return
     */
    @GetMapping("/list")
    public Result<List<CategoryVO>> getCategoryList() {
        return categoryService.getCategoryList();
    }

}
