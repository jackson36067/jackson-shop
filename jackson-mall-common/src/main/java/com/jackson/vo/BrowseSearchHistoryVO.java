package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BrowseSearchHistoryVO implements Serializable {
    private Long id;
    private String value;
    private LocalDate searchDate;

    public BrowseSearchHistoryVO() {
    }

    public BrowseSearchHistoryVO(Long id, String value, LocalDate searchDate) {
        this.id = id;
        this.value = value;
        this.searchDate = searchDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        this.searchDate = searchDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BrowseSearchHistoryVO that = (BrowseSearchHistoryVO) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(searchDate, that.searchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, searchDate);
    }

    @Override
    public String toString() {
        return "BrowseSearchHistoryVO{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", searchDate=" + searchDate +
                '}';
    }
}
