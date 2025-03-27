package com.jackson.service;

import com.jackson.dto.AddBrowseSearchHistoryDTO;
import com.jackson.dto.RemoveBrowseHistoryDTO;
import com.jackson.result.Result;
import com.jackson.vo.MemberBrowseHistoryVO;

import java.time.LocalDate;
import java.util.List;

public interface MemberBrowseHistoryService {
    Result<List<MemberBrowseHistoryVO>> getMemberBrowseHistoryList(Short type, String goodsName, LocalDate begin);

    void removeMemberBrowseHistory(RemoveBrowseHistoryDTO removeBrowseHistoryDTO);

    void addBrowseSearchInfo(AddBrowseSearchHistoryDTO addBrowseSearchHistoryDTO);

    Result<List<String>> getBrowseSearchHistoryList();

    void removeAllBrowseSearchHistory();
}
