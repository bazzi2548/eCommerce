package com.example.goodsservice.dto.request;

import lombok.Getter;

@Getter
public class SaveGoodsRequest {

    private String name;
    private Long price;
    private int stock;
    private Integer category_id;
}
