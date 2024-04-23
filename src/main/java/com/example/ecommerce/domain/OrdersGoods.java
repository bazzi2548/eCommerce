package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OrdersGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersGoodsId;

    private Long ordersId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private int count;

    protected OrdersGoods() {}

    public OrdersGoods(Long ordersId, Goods goods, int count) {
        this.ordersId = ordersId;
        this.goods = goods;
        this.count = count;
    }
}
