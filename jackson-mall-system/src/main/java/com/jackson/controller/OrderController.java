package com.jackson.controller;

import com.jackson.dto.OrderDTO;
import com.jackson.result.Result;
import com.jackson.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 生成订单
     * @param orderDTO 订单相关信息
     */
    @PostMapping
    public Result<String> generateOrder(@RequestBody OrderDTO orderDTO) {
         return orderService.generateOrder(orderDTO);
    }
}
