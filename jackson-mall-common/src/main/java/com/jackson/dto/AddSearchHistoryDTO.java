package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddSearchHistoryDTO implements Serializable {
    private String keyword;

    public AddSearchHistoryDTO() {
    }

    public AddSearchHistoryDTO(String keyword) {
        this.keyword = keyword;
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
        AddSearchHistoryDTO that = (AddSearchHistoryDTO) o;
        return Objects.equals(keyword, that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(keyword);
    }

    @Override
    public String toString() {
        return "AddSearchHistoryDTO{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
