package com.jackson.controller;

import com.jackson.result.Result;
import com.jackson.service.ColumnService;
import com.jackson.vo.ColumnDetailVO;
import com.jackson.vo.ColumnHomeVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/column")
public class ColumnController {

    @Resource
    private ColumnService columnService;

    /**
     * 获取首页栏列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<ColumnHomeVO>> getColumnList(){
        return columnService.getColumnList();
    }

    /**
     * 通过id获取栏目详情信息
     * @param id 栏目id
     * @return 包含栏目id,名称以及其中的商品信息
     */
    @GetMapping("/detail/{id}")
    public Result<ColumnDetailVO> getColumnDetailById(@PathVariable Long id){
        return columnService.getColumnDetailById(id);
    }
}
