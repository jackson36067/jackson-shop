package com.jackson.service;

import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.GoodsCommentVO;

public interface CommentService {
    Result<PageResult<GoodsCommentVO>> getGoodsComment(Long goodsId, Integer type, Integer page, Integer pageSize);
}
