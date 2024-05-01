package com.example.ordersservice.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OrdersGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersGoodsId;

    private Long ordersId;

    private Long goodsId;

    private String goodsName;

    private int count;

    protected OrdersGoods() {}

    public OrdersGoods(Long ordersId, Long goodsId, String goodsName, int count) {
        this.ordersId = ordersId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.count = count;
    }
}
