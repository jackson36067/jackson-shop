package com.jackson.service;

import com.jackson.dto.CancelFollowStoreDTO;
import com.jackson.dto.FollowStoreDTO;
import com.jackson.result.Result;
import com.jackson.vo.FollowStoreVO;
import com.jackson.vo.StoreVO;

import java.util.List;

public interface StoreService {
    Result<StoreVO> getStoreInfoById(Long id);

    void followStore(FollowStoreDTO followStoreDTO);

    Result<List<FollowStoreVO>> getFollowStoreList(String name);

    void cancelFollowStore(CancelFollowStoreDTO cancelFollowStoreDTO);
}
