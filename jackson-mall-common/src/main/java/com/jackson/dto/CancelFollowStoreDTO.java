package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CancelFollowStoreDTO implements Serializable {
    private List<Long> idList;

    public CancelFollowStoreDTO() {
    }

    public CancelFollowStoreDTO(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CancelFollowStoreDTO that = (CancelFollowStoreDTO) o;
        return Objects.equals(idList, that.idList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idList);
    }

    @Override
    public String toString() {
        return "CancelFollowStoreDTO{" +
                "idList=" + idList +
                '}';
    }
}
