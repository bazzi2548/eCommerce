package com.example.goodsservice.dto.response;

import com.example.goodsservice.domain.Goods;
import lombok.Getter;

@Getter
public class GoodsResponse {
    private String name;
    private Long price;

    public GoodsResponse(Goods goods) {
        name = goods.getName();
        price = goods.getPrice();
    }
}
