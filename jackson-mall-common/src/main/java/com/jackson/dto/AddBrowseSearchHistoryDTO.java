package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddBrowseSearchHistoryDTO implements Serializable {
    private String value;

    public AddBrowseSearchHistoryDTO() {
    }
    public AddBrowseSearchHistoryDTO(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddBrowseSearchHistoryDTO that = (AddBrowseSearchHistoryDTO) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "AddBrowseSearchHistoryDTO{" +
                "value='" + value + '\'' +
                '}';
    }
}
