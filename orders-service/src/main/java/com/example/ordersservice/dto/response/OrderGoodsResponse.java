package com.example.ordersservice.dto.response;

import com.example.ordersservice.domain.OrdersGoods;
import lombok.Getter;

@Getter
public class OrderGoodsResponse {
    private Long ordersGoodsId;
    private String goodsName;
    private int count;

    public OrderGoodsResponse(OrdersGoods ordersGoods) {
        ordersGoodsId = ordersGoods.getOrdersGoodsId();
        goodsName = ordersGoods.getGoodsName();
        count = ordersGoods.getCount();
    }
}
