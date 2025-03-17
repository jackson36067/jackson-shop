package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class SearchHistoryVO implements Serializable {
    private Long id;
    private String keyword;

    public SearchHistoryVO() {
    }

    public SearchHistoryVO(Long id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchHistoryVO that = (SearchHistoryVO) o;
        return Objects.equals(id, that.id) && Objects.equals(keyword, that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyword);
    }

    @Override
    public String toString() {
        return "SearchHistoryVO{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
