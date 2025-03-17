package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class KeyWordVO implements Serializable {
    private Long id;
    private String keyword;
    public KeyWordVO() {
    }

    public KeyWordVO(Long id, String keyword) {
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
    public String toString() {
        return "KeyWordVO{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
