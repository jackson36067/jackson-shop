package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class FollowStoreDTO implements Serializable {
    private Long id;
    private Boolean isFollow;

    public FollowStoreDTO() {
    }

    public FollowStoreDTO(Long id, Boolean isFollow) {
        this.id = id;
        this.isFollow = isFollow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FollowStoreDTO that = (FollowStoreDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(isFollow, that.isFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isFollow);
    }

    @Override
    public String toString() {
        return "FollowStoreDTO{" +
                "id=" + id +
                ", isFollow=" + isFollow +
                '}';
    }
}
