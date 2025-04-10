package com.jackson.service;

import com.jackson.dto.OrderDTO;
import com.jackson.result.Result;

public interface OrderService {
    Result<String> generateOrder(OrderDTO orderDTO);
}
