package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RemoveBrowseHistoryDTO implements Serializable {
    private List<Long> idList;
    public RemoveBrowseHistoryDTO() {

    }
    public RemoveBrowseHistoryDTO(List<Long> idList) {
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
        RemoveBrowseHistoryDTO that = (RemoveBrowseHistoryDTO) o;
        return Objects.equals(idList, that.idList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idList);
    }

    @Override
    public String toString() {
        return "RemoveBrowseHistoryDTO{" +
                "idList=" + idList +
                '}';
    }
}
