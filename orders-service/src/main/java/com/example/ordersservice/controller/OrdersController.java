package com.example.ordersservice.controller;

import com.example.ordersservice.dto.request.OrdersRequest;
import com.example.ordersservice.dto.response.OrderGoodsResponse;
import com.example.ordersservice.dto.response.OrdersResponse;
import com.example.ordersservice.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
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
    public List<OrdersResponse> getOrders(@CookieValue("access") String token) {
        return ordersService.getOrders(token);
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
