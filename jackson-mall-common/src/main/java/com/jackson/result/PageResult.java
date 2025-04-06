package com.jackson.result;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PageResult<T> implements Serializable {
    private List<T> data;
    private Boolean isRemain; // 是否剩余内容

    public PageResult() {
    }

    public PageResult(List<T> data, Boolean isRemain) {
        this.data = data;
        this.isRemain = isRemain;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getIsRemain() {
        return isRemain;
    }

    public void setIsRemain(Boolean remain) {
        isRemain = remain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResult<?> that = (PageResult<?>) o;
        return Objects.equals(data, that.data) && Objects.equals(isRemain, that.isRemain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, isRemain);
    }

    @Override
    public String toString() {
        return "GoodsPageResult{" +
                "data=" + data +
                ", isRemain=" + isRemain +
                '}';
    }
}
