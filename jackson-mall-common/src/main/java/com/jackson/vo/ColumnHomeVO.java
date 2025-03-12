package com.jackson.vo;

import java.util.Objects;

public class ColumnHomeVO {
    private Long id;
    private String name;
    private String bgPic;

    public ColumnHomeVO() {
    }

    public ColumnHomeVO(Long id, String name, String bgPic) {
        this.id = id;
        this.name = name;
        this.bgPic = bgPic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgPic() {
        return bgPic;
    }

    public void setBgPic(String bgPic) {
        this.bgPic = bgPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnHomeVO that = (ColumnHomeVO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(bgPic, that.bgPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bgPic);
    }

    @Override
    public String toString() {
        return "ColumnHomeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bgPic='" + bgPic + '\'' +
                '}';
    }
}
