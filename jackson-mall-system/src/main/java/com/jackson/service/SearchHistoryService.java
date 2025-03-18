package com.jackson.service;

import com.jackson.dto.AddSearchHistoryDTO;
import com.jackson.result.Result;
import com.jackson.vo.SearchHistoryAndKeyWordsVO;

public interface SearchHistoryService {

    /**
     * 获取用户搜索历史记录以及热门搜索词
     *
     * @return
     */
    Result<SearchHistoryAndKeyWordsVO> getHistoryAndKeywordList();

    void deleteAllSearchHistory();

    void addSearchHistory(AddSearchHistoryDTO addSearchHistoryDTO);
}
