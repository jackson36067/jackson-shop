package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class GoodsProductVO implements Serializable {
    private Long id;
    private BigDecimal price;
    private Integer number;
    private String url;
    private Boolean defaultSelected;
    private Map<String, String> specs;

    public GoodsProductVO() {
    }

    public GoodsProductVO(Long id, BigDecimal price, Integer number, String url, Boolean defaultSelected, Map<String, String> specs) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.url = url;
        this.defaultSelected = defaultSelected;
        this.specs = specs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getDefaultSelected() {
        return defaultSelected;
    }

    public void setDefaultSelected(Boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    public Map<String, String> getSpecs() {
        return specs;
    }

    public void setSpecs(Map<String, String> specs) {
        this.specs = specs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsProductVO that = (GoodsProductVO) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(number, that.number) && Objects.equals(url, that.url) && Objects.equals(defaultSelected, that.defaultSelected) && Objects.equals(specs, that.specs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, number, url, defaultSelected, specs);
    }

    @Override
    public String toString() {
        return "GoodsProductVO{" +
                "id=" + id +
                ", price=" + price +
                ", number=" + number +
                ", url='" + url + '\'' +
                ", defaultSelected=" + defaultSelected +
                ", specs=" + specs +
                '}';
    }
}