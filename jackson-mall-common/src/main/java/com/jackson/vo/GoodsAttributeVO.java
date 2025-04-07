package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class GoodsAttributeVO implements Serializable {
    private Long id;
    private String attribute;
    private String value;

    public GoodsAttributeVO() {
    }

    public GoodsAttributeVO(Long id, String attribute, String value) {
        this.id = id;
        this.attribute = attribute;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
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
        GoodsAttributeVO that = (GoodsAttributeVO) o;
        return Objects.equals(id, that.id) && Objects.equals(attribute, that.attribute) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attribute, value);
    }

    @Override
    public String toString() {
        return "GoodsAttributeVO{" +
                "id=" + id +
                ", attribute='" + attribute + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
