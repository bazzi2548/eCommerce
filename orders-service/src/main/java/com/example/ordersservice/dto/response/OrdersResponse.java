package com.example.ordersservice.dto.response;

import com.example.ordersservice.domain.Orders;
import com.example.ordersservice.domain.StatusEnum;
import lombok.Getter;

@Getter
public class OrdersResponse {

    private Long ordersId;
    private Long totalPrice;
    private StatusEnum status;

    public OrdersResponse(Orders orders) {
        ordersId = orders.getOrdersId();
        totalPrice = orders.getTotalPrice();
        status = orders.getStatus();
    }
}

