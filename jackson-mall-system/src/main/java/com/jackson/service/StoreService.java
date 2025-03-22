package com.jackson.service;

import com.jackson.dto.FollowStoreDTO;
import com.jackson.result.Result;
import com.jackson.vo.StoreVO;

public interface StoreService {
    Result<StoreVO> getStoreInfoById(Long id);

    void followStore(FollowStoreDTO followStoreDTO);
}
