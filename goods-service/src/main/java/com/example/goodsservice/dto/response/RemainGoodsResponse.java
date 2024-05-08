package com.example.goodsservice.dto.response;

import lombok.Getter;

@Getter
public class RemainGoodsResponse {

    private Long id;
    private int count;

    public RemainGoodsResponse(Long id, int count) {
        this.id = id;
        this.count = count;
    }
}
