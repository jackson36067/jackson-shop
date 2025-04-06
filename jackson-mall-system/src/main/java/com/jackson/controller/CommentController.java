package com.jackson.controller;

import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.service.CommentService;
import com.jackson.vo.GoodsCommentVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取商品评论列表
     *
     * @param goodsId  商品id
     * @param type     0.全部评论 1.好评 2.中评 3.差评 4.有图
     * @param page     页码
     * @param pageSize 页码数
     * @return 商品评论列表
     */
    @GetMapping
    public Result<PageResult<GoodsCommentVO>> getGoodsComment(Long goodsId, Integer type, Integer page, Integer pageSize) {
        return commentService.getGoodsComment(goodsId, type, page, pageSize);
    }
}
