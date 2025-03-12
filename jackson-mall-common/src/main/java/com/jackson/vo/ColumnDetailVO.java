package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ColumnDetailVO implements Serializable {
    private Long id;
    private String name;
    private String detailPic;
    private List<GoodsMessageVO> goodsMessageVOList;

    public ColumnDetailVO() {
    }

    public ColumnDetailVO(Long id, String name, String detailPic, List<GoodsMessageVO> goodsMessageVOList) {
        this.id = id;
        this.name = name;
        this.detailPic = detailPic;
        this.goodsMessageVOList = goodsMessageVOList;
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

    public String getDetailPic() {
        return detailPic;
    }

    public void setDetailPic(String detailPic) {
        this.detailPic = detailPic;
    }

    public List<GoodsMessageVO> getGoodsMessageVOList() {
        return goodsMessageVOList;
    }

    public void setGoodsMessageVOList(List<GoodsMessageVO> goodsMessageVOList) {
        this.goodsMessageVOList = goodsMessageVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnDetailVO that = (ColumnDetailVO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(detailPic, that.detailPic) && Objects.equals(goodsMessageVOList, that.goodsMessageVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, detailPic, goodsMessageVOList);
    }

    @Override
    public String toString() {
        return "ColumnDetailVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detailPic='" + detailPic + '\'' +
                ", goodsMessageVOList=" + goodsMessageVOList +
                '}';
    }
}
