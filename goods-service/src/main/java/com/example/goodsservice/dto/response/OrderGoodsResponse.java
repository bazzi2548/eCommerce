package com.example.goodsservice.dto.response;

import com.example.goodsservice.domain.OrdersGoods;
import lombok.Getter;

@Getter
public class OrderGoodsResponse {
    private Long ordersGoodsId;
    private String goodsName;
    private int count;

    public OrderGoodsResponse(OrdersGoods ordersGoods) {
        ordersGoodsId = ordersGoods.getOrdersGoodsId();
        goodsName = ordersGoods.getGoods().getName();
        count = ordersGoods.getCount();
    }
}
