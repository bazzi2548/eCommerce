package com.example.goodsservice.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(nullable = false)
    private int count;

    protected Wishlist() {
    }

    public Wishlist(Long userId, Goods goods, int count) {
        this.userId = userId;
        this.goods = goods;
        this.count = count;
    }

    public void updateCount(int count) {
        this.count = count;
    }
}
