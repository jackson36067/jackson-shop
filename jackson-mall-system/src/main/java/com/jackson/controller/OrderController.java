package com.jackson.controller;

import com.jackson.dto.OrderDTO;
import com.jackson.result.Result;
import com.jackson.service.OrderService;
import com.jackson.vo.OrderVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 生成订单
     *
     * @param orderDTO 订单相关信息
     */
    @PostMapping
    public Result<String> generateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.generateOrder(orderDTO);
    }

    /**
     * @param type                    获取订单类型 0.全部  1.待支付 2.代发货 3.待收货 4.已完成
     * @param goodsNameOrOrderSnParam 订单编号或者订单中商品名称
     * @return 订单数据列表
     */
    @GetMapping("/list")
    public Result<List<OrderVO>> getOrderList(Integer type, @RequestParam(required = false) String goodsNameOrOrderSnParam, @RequestParam(required = false)LocalDateTime placeOrderBeginTimeParam,@RequestParam(required = false)LocalDateTime placeOrderEndTimeParam) {
        return orderService.getOrderList(type, goodsNameOrOrderSnParam, placeOrderBeginTimeParam,placeOrderEndTimeParam);
    }
}
