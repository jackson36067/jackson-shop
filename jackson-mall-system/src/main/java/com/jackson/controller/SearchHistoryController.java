package com.jackson.controller;

import com.jackson.result.Result;
import com.jackson.service.SearchHistoryService;
import com.jackson.vo.SearchHistoryAndKeyWordsVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchHistoryController {

    @Resource
    private SearchHistoryService searchHistoryService;

    /**
     * 获取用户搜索历史记录以及热门搜索词
     * @return
     */
    @GetMapping("/list")
    public Result<SearchHistoryAndKeyWordsVO> getHistoryAndKeywordList() {
        return searchHistoryService.getHistoryAndKeywordList();
    }

    /**
     * 删除用户所有查询记录
     */
    @DeleteMapping("/delete/all")
    public void deleteAllSearchHistory(){
        searchHistoryService.deleteAllSearchHistory();
    }
}
