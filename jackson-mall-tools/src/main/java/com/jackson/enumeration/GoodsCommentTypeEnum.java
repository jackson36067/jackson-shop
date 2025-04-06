package com.jackson.enumeration;

import lombok.Getter;

@Getter
public enum GoodsCommentTypeEnum {
    ALL(0),
    GOOD(1),
    NATURAL(2),
    BAD(3),
    HAS_PICTURE(4);

    private final Integer type;
    private GoodsCommentTypeEnum(Integer type) {
        this.type = type;
    }
}
