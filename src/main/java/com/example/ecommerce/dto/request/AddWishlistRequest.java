package com.example.ecommerce.dto.request;

import lombok.Getter;

@Getter
public class AddWishlistRequest {
    private Long goodsId;
    private int count;
}
