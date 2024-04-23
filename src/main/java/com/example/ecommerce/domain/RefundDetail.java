package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RefundDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundDetailId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_goods_id")
    private OrdersGoods ordersGoods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refund_id")
    private Refund refund;

    private int count;

    protected RefundDetail() {}
}
