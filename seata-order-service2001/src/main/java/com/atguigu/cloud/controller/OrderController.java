package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @GetMapping("/order/create")
    public ResultData create(Order order){
        orderService.create(order);
        return ResultData.success(order);
    }
}
