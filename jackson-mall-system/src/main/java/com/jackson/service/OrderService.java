package com.jackson.service;

import com.jackson.dto.OrderDTO;
import com.jackson.result.Result;
import com.jackson.vo.OrderDataVO;
import com.jackson.vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Result<String> generateOrder(OrderDTO orderDTO);
    Result<List<OrderVO>> getOrderList(Integer type, String goodsNameOrOrderSnParam, LocalDateTime placeOrderTimeParam, LocalDateTime placeOrderEndTimeParam);

    Result<OrderDataVO> getOrderTypeNumberData();
}
