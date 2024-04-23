package com.example.ecommerce.dto.response;

import com.example.ecommerce.domain.Orders;
import com.example.ecommerce.domain.StatusEnum;
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

