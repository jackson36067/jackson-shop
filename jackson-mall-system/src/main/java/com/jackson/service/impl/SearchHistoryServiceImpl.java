package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.AddSearchHistoryDTO;
import com.jackson.entity.ShopSearchHistory;
import com.jackson.repository.KeyWordsRepository;
import com.jackson.repository.SearchHistoryRepository;
import com.jackson.result.Result;
import com.jackson.service.SearchHistoryService;
import com.jackson.vo.KeyWordVO;
import com.jackson.vo.SearchHistoryAndKeyWordsVO;
import com.jackson.vo.SearchHistoryVO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
        // 判断用户是否登录, 没有登录就返回热门搜索, 登录则把用户历史搜索记录也一起返回
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
        // 获取10条最近搜索记录
        PageRequest pageRequest = PageRequest.of(0, 9);
        List<ShopSearchHistory> shopSearchHistoryList = searchHistoryRepository.findAllByUserId(userId, pageRequest);
        List<SearchHistoryVO> searchHistoryVOList = shopSearchHistoryList.stream()
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

    /**
     * 新增用户历史查询记录
     *
     * @param addSearchHistoryDTO
     */
    public void addSearchHistory(AddSearchHistoryDTO addSearchHistoryDTO) {
        // 先判断该用户是否以及搜索过该词
        Long userId = BaseContext.getCurrentId();
        ShopSearchHistory shopSearchHistory = searchHistoryRepository.findByKeywordAndUserId(addSearchHistoryDTO.getKeyword(), userId);
        if (shopSearchHistory != null) {
            shopSearchHistory.setCreateTime(LocalDateTime.now());
            searchHistoryRepository.saveAndFlush(shopSearchHistory);
        } else {
            shopSearchHistory = new ShopSearchHistory();
            shopSearchHistory.setKeyword(addSearchHistoryDTO.getKeyword());
            shopSearchHistory.setUserId(userId);
            shopSearchHistory.setHasGoods(true);
            searchHistoryRepository.save(shopSearchHistory);
        }
    }
}
