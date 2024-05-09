package com.example.ordersservice.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long goodsId;

    @Column(nullable = false)
    private String goodsName;

    @Column(nullable = false)
    private int count;

    protected Wishlist() {
    }

    public Wishlist(Long userId, Long goodsId, String goodsName, int count) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.count = count;
    }

    public void updateCount(int count) {
        this.count = count;
    }
}
