package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.CommentConstant;
import com.jackson.entity.ShopComment;
import com.jackson.entity.ShopMember;
import com.jackson.enumeration.GoodsCommentTypeEnum;
import com.jackson.repository.CommentRepository;
import com.jackson.repository.MemberRepository;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.service.CommentService;
import com.jackson.vo.GoodsCommentVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;
    @Resource
    private MemberRepository memberRepository;

    /**
     * 获取商品评论列表
     *
     * @param goodsId  商品id
     * @param type     0.全部评论 1.好评 2.中评 3.差评 4.有图
     * @param page     页码
     * @param pageSize 页码数
     * @return 店铺评论列表
     */
    public Result<PageResult<GoodsCommentVO>> getGoodsComment(Long goodsId, Integer type, Integer page, Integer pageSize) {
        Specification<ShopComment> shopCommentSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();
            Predicate goodsIdCondition = cb.equal(root.get(CommentConstant.VALUE_ID), goodsId);
            predicateArrayList.add(goodsIdCondition);
            // 根据类型判断获取对应条件的评论
            switch (GoodsCommentTypeEnum.values()[type]) {
                case ALL:
                    // 全部评论, 不需要添加条件
                    break;
                case GOOD:
                    // 好评 5颗星
                    Predicate goodCommentCondition = cb.equal(root.get(CommentConstant.STAR), CommentConstant.GOOD_COMMENT);
                    predicateArrayList.add(goodCommentCondition);
                    break;
                case NATURAL:
                    // 中评 2-4颗星
                    Predicate naturalCommentCondition = cb.between(root.get(CommentConstant.STAR), CommentConstant.NATURAL_COMMENT_START, CommentConstant.NATURAL_COMMENT_END);
                    predicateArrayList.add(naturalCommentCondition);
                    break;
                case BAD:
                    // 差评 1颗星
                    Predicate badCommentCondition = cb.equal(root.get(CommentConstant.STAR), CommentConstant.BAD_COMMENT);
                    predicateArrayList.add(badCommentCondition);
                    break;
                case HAS_PICTURE:
                    // 有图
                    Predicate hasPictureCondition = cb.equal(root.get(CommentConstant.HAS_PICTURE), true);
                    predicateArrayList.add(hasPictureCondition);
                    break;
            }
            return cb.and(predicateArrayList.toArray(new Predicate[0]));
        };
        Sort sort = Sort.by(Sort.Direction.DESC, CommentConstant.CREATE_TIME);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<ShopComment> shopCommentPage = commentRepository.findAll(shopCommentSpecification, pageRequest);
        boolean isRemain = true;
        List<GoodsCommentVO> goodsCommentVOList = List.of();
        if (shopCommentPage.getContent().isEmpty()) {
            isRemain = false;
        } else {
            goodsCommentVOList = shopCommentPage.getContent()
                    .stream()
                    .map(shopComment -> {
                        GoodsCommentVO goodsCommentVO = BeanUtil.copyProperties(shopComment, GoodsCommentVO.class);
                        ShopMember shopMember = memberRepository.findById(shopComment.getUserId()).get();
                        goodsCommentVO.setAvatar(shopMember.getAvatar());
                        goodsCommentVO.setNickname(shopMember.getNickname());
                        goodsCommentVO.setPicUrls(JSONUtil.toList(shopComment.getPicUrls(), String.class));
                        return goodsCommentVO;
                    })
                    .toList();
        }
        PageResult<GoodsCommentVO> goodsCommentVOPageResult = new PageResult<>();
        goodsCommentVOPageResult.setData(goodsCommentVOList);
        goodsCommentVOPageResult.setIsRemain(isRemain);
        return Result.success(goodsCommentVOPageResult);
    }
}
