package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MemberBrowseHistoryVO<T> implements Serializable {
    private Long id;
    private String browseTime;
    private LocalDate browseDate;
    private List<T> data;

    public MemberBrowseHistoryVO() {
    }

    public MemberBrowseHistoryVO(Long id, String browseTime, LocalDate browseDate, List<T> data) {
        this.id = id;
        this.browseTime = browseTime;
        this.browseDate = browseDate;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(String browseTime) {
        this.browseTime = browseTime;
    }

    public LocalDate getBrowseDate() {
        return browseDate;
    }

    public void setBrowseDate(LocalDate browseDate) {
        this.browseDate = browseDate;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberBrowseHistoryVO<?> that = (MemberBrowseHistoryVO<?>) o;
        return Objects.equals(id, that.id) && Objects.equals(browseTime, that.browseTime) && Objects.equals(browseDate, that.browseDate) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, browseTime, browseDate, data);
    }

    @Override
    public String toString() {
        return "MemberBrowseHistoryVO{" +
                "id=" + id +
                ", browseTime='" + browseTime + '\'' +
                ", browseDate=" + browseDate +
                ", data=" + data +
                '}';
    }
}
