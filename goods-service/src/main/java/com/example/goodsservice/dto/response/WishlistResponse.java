package com.example.goodsservice.dto.response;

import com.example.goodsservice.domain.Wishlist;
import lombok.Getter;

@Getter
public class WishlistResponse {
    private Long wishlistId;
    private String name;
    private int count;

    public WishlistResponse(Wishlist wishlist) {
        wishlistId = wishlist.getWishlistId();
        name = wishlist.getGoods().getName();
        count = wishlist.getCount();
    }
}
