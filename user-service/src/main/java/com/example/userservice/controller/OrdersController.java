package com.example.userservice.controller;

import com.example.userservice.dto.request.OrdersRequest;
import com.example.userservice.dto.response.OrderGoodsResponse;
import com.example.userservice.dto.response.OrdersResponse;
import com.example.userservice.service.OrdersService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // 주문 요청
    @PostMapping("/orders")
    public void requestOrders(@RequestBody OrdersRequest request) {
        ordersService.requestOrders(request);
    }

    // 주문 조회
    @GetMapping("/orders")
    public List<OrdersResponse> getOrders(Authentication auth) {
        return ordersService.getOrders(auth);
    }

    // 주문 상세 조회
    @GetMapping("/orders/{orders_id}")
    public List<OrderGoodsResponse> getDetailOrders(@PathVariable long orders_id) {
        return ordersService.getDetailOrders(orders_id);
    }

    // 주문 취소
    @PostMapping("/orders/{orders_id}")
    public void cancelOrders(@PathVariable Long orders_id) {
        ordersService.cancelOrders(orders_id);
    }

    // 반품 요청
    @PostMapping("/orders/return/{orders_id}")
    public void returnOrders(@PathVariable Long orders_id) {
        ordersService.returnOrders(orders_id);
    }

}
