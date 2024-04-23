package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.OrdersRequest;
import com.example.ecommerce.service.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
