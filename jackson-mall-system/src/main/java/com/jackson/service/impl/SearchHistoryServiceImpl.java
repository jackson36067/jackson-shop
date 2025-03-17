package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.context.BaseContext;
import com.jackson.entity.ShopSearchHistory;
import com.jackson.repository.KeyWordsRepository;
import com.jackson.repository.SearchHistoryRepository;
import com.jackson.result.Result;
import com.jackson.service.SearchHistoryService;
import com.jackson.vo.KeyWordVO;
import com.jackson.vo.SearchHistoryAndKeyWordsVO;
import com.jackson.vo.SearchHistoryVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Resource
    private SearchHistoryRepository searchHistoryRepository;
    @Resource
    private KeyWordsRepository keyWordsRepository;

    /**
     * 获取用户搜索历史记录以及热门搜索词
     *
     * @return
     */
    public Result<SearchHistoryAndKeyWordsVO> getHistoryAndKeywordList() {
        Long userId = BaseContext.getCurrentId();
        List<KeyWordVO> keywordVOList = keyWordsRepository.findAllByIsHotOrderBySortAsc(true)
                .stream()
                .map(shopKeyword -> BeanUtil.copyProperties(shopKeyword, KeyWordVO.class))
                .toList();
        SearchHistoryAndKeyWordsVO searchHistoryAndKeyWordsVO = new SearchHistoryAndKeyWordsVO(null, keywordVOList);
        // 如果没有登录
        if (userId == null) {
            return Result.success(searchHistoryAndKeyWordsVO);
        }
        List<SearchHistoryVO> searchHistoryVOList = searchHistoryRepository.findAllByUserId(userId)
                .stream()
                .map(shopSearchHistory -> BeanUtil.copyProperties(shopSearchHistory, SearchHistoryVO.class))
                .toList();
        searchHistoryAndKeyWordsVO.setSearchHistoryVOList(searchHistoryVOList);
        return Result.success(searchHistoryAndKeyWordsVO);
    }

    /**
     * 删除用户所有查询记录
     */
    public void deleteAllSearchHistory() {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            return;
        }
        searchHistoryRepository.deleteAllByUserId(userId);
    }
}
