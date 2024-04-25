package com.example.goodsservice.dto.response;

import com.example.goodsservice.domain.Goods;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetailGoodsResponse {
    private Long goodsId;
    private String name;
    private Long price;
    private int stock;
    private LocalDateTime updatedAt;

    public DetailGoodsResponse(Goods goods) {
        goodsId = goods.getGoodsId();
        name = goods.getName();
        price = goods.getPrice();
        stock = goods.getStock();
        updatedAt = goods.getUpdatedAt();
    }
}
