package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SearchHistoryAndKeyWordsVO implements Serializable {
    private List<SearchHistoryVO> searchHistoryVOList;
    private List<KeyWordVO> keyWordVOList;

    public SearchHistoryAndKeyWordsVO() {
    }

    public SearchHistoryAndKeyWordsVO(List<SearchHistoryVO> searchHistoryVOList, List<KeyWordVO> keyWordVOList) {
        this.searchHistoryVOList = searchHistoryVOList;
        this.keyWordVOList = keyWordVOList;
    }

    public List<SearchHistoryVO> getSearchHistoryVOList() {
        return searchHistoryVOList;
    }

    public void setSearchHistoryVOList(List<SearchHistoryVO> searchHistoryVOList) {
        this.searchHistoryVOList = searchHistoryVOList;
    }

    public List<KeyWordVO> getKeyWordVOList() {
        return keyWordVOList;
    }

    public void setKeyWordVOList(List<KeyWordVO> keyWordVOList) {
        this.keyWordVOList = keyWordVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchHistoryAndKeyWordsVO that = (SearchHistoryAndKeyWordsVO) o;
        return Objects.equals(searchHistoryVOList, that.searchHistoryVOList) && Objects.equals(keyWordVOList, that.keyWordVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchHistoryVOList, keyWordVOList);
    }

    @Override
    public String toString() {
        return "SearchHistoryAndKeyWordsVO{" +
                "searchHistoryVOList=" + searchHistoryVOList +
                ", keyWordVOList=" + keyWordVOList +
                '}';
    }
}
