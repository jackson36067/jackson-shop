package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    Result<List<CategoryVO>> getCategoryList();
}
