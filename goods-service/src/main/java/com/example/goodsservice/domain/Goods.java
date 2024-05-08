package com.example.goodsservice.domain;

import com.example.goodsservice.dto.request.SaveGoodsRequest;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean checkSoldOut;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Goods() {
    }

    public Goods(SaveGoodsRequest request) {
        name = request.getName();
        price = request.getPrice();
        stock = request.getStock();
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reduceStock(int stock) {
        this.stock -= stock;
    }

    public void increaseStock(int stock) {
        this.stock += stock;
    }
}
