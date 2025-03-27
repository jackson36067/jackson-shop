package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class StoreHistoryVO implements Serializable {
    private Long id; // 店铺id
    private Long browseId; // 用户浏览记录id
    private String storeName;
    private String avatar;
    private Integer followNumber;
    private List<GoodsHistoryVO> goodsHistoryVOList;

    public StoreHistoryVO() {
    }

    public StoreHistoryVO(Long id,Long browseId, String storeName,String avatar, Integer followNumber, List<GoodsHistoryVO> goodsHistoryVOList) {
        this.id = id;
        this.browseId = browseId;
        this.storeName = storeName;
        this.avatar = avatar;
        this.followNumber = followNumber;
        this.goodsHistoryVOList = goodsHistoryVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrowseId() {
        return browseId;
    }

    public void setBrowseId(Long browseId) {
        this.browseId = browseId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
    }

    public List<GoodsHistoryVO> getGoodsHistoryVOList() {
        return goodsHistoryVOList;
    }

    public void setGoodsHistoryVOList(List<GoodsHistoryVO> goodsHistoryVOList) {
        this.goodsHistoryVOList = goodsHistoryVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StoreHistoryVO that = (StoreHistoryVO) o;
        return Objects.equals(id, that.id) && Objects.equals(browseId, that.browseId) && Objects.equals(storeName, that.storeName) && Objects.equals(avatar, that.avatar) && Objects.equals(followNumber, that.followNumber) && Objects.equals(goodsHistoryVOList, that.goodsHistoryVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, browseId, storeName, avatar, followNumber, goodsHistoryVOList);
    }

    @Override
    public String toString() {
        return "StoreHistoryVO{" +
                "id=" + id +
                ", browseId=" + browseId +
                ", storeName='" + storeName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", followNumber=" + followNumber +
                ", goodsHistoryVOList=" + goodsHistoryVOList +
                '}';
    }
}
