package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.OrdersRequest;
import com.example.ecommerce.dto.response.OrderGoodsResponse;
import com.example.ecommerce.dto.response.OrdersResponse;
import com.example.ecommerce.service.OrdersService;
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


    @PostMapping("/orders")
    public void requestOrders(@RequestBody OrdersRequest request) {
        ordersService.requestOrders(request);
    }

    @GetMapping("/orders")
    public List<OrdersResponse> getOrders(Authentication auth) {
        return ordersService.getOrders(auth);
    }

    @GetMapping("/orders/{orders_id}")
    public List<OrderGoodsResponse> getDetailOrders(@PathVariable long orders_id) {
        return ordersService.getDetailOrders(orders_id);
    }

    @PostMapping("/orders/{orders_id}")
    public void cancelOrders(@PathVariable Long orders_id) {
        ordersService.cancelOrders(orders_id);
    }

}
