package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.ColumnDetailVO;
import com.jackson.vo.ColumnHomeVO;

import java.util.List;

public interface ColumnService {
    Result<List<ColumnHomeVO>> getColumnList();

    Result<ColumnDetailVO> getColumnDetailById(Long id);
}
