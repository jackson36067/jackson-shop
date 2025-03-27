package com.jackson.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackson.dto.AddBrowseSearchHistoryDTO;
import com.jackson.dto.RemoveBrowseHistoryDTO;
import com.jackson.result.Result;
import com.jackson.service.MemberBrowseHistoryService;
import com.jackson.vo.MemberBrowseHistoryVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/browse")
public class MemberBrowseHistoryController {

    @Resource
    private MemberBrowseHistoryService memberBrowseHistoryService;

    /**
     * 获取用户历史浏览记录
     *
     * @param type      浏览记录类型 0.商品 1.店铺 2.评论
     * @param goodsName 商品名称
     * @param begin     时间筛选
     * @return MemberBrowseHistoryVO
     */
    @GetMapping("/list")
    public Result<List<MemberBrowseHistoryVO>> getMemberBrowseHistoryList(Short type, @RequestParam(required = false) String goodsName, @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDate begin) {
        return memberBrowseHistoryService.getMemberBrowseHistoryList(type, goodsName, begin);
    }

    /**
     * 删除用户历史浏览记录
     *
     * @param removeBrowseHistoryDTO 用户历史浏览记录id
     */
    @DeleteMapping("/remove")
    public void removeMemberBrowseHistory(@RequestBody RemoveBrowseHistoryDTO removeBrowseHistoryDTO) {
        memberBrowseHistoryService.removeMemberBrowseHistory(removeBrowseHistoryDTO);
    }

    /**
     * 通过redis保存用户的浏览搜索历史记录, 设置过期时间为7天
     *
     * @param addBrowseSearchHistoryDTO 搜索名称
     */
    @PostMapping("/search")
    public void addBrowseSearchInfo(@RequestBody AddBrowseSearchHistoryDTO addBrowseSearchHistoryDTO) {
        memberBrowseHistoryService.addBrowseSearchInfo(addBrowseSearchHistoryDTO);
    }

    /**
     * 获取用户浏览记录搜索列表
     *
     * @return 用户搜索记录
     */
    @GetMapping("/search/list")
    public Result<List<String>> getBrowseSearchHistoryList() {
        return memberBrowseHistoryService.getBrowseSearchHistoryList();
    }

    /**
     * 移除用户浏览记录搜索列表
     */
    @PostMapping("/search/remove")
    public void removeAllBrowseSearchHistory() {
        memberBrowseHistoryService.removeAllBrowseSearchHistory();
    }
}
