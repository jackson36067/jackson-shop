package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UpdateGoodsCheckedDTO implements Serializable {
    private List<Long> ids;
    private Boolean checked;
    private Short number;

    public UpdateGoodsCheckedDTO() {
    }

    public UpdateGoodsCheckedDTO(List<Long> ids, Boolean checked, Short number) {
        this.ids = ids;
        this.checked = checked;
        this.number = number;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGoodsCheckedDTO that = (UpdateGoodsCheckedDTO) o;
        return Objects.equals(ids, that.ids) && Objects.equals(checked, that.checked) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ids, checked, number);
    }

    @Override
    public String toString() {
        return "UpdateGoodsCheckedDTO{" +
                "ids=" + ids +
                ", checked=" + checked +
                ", number=" + number +
                '}';
    }
}
