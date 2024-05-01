package com.example.ordersservice.dto.response;

import com.example.ordersservice.domain.Wishlist;
import lombok.Getter;

@Getter
public class WishlistResponse {
    private Long wishlistId;
    private String name;
    private int count;

    public WishlistResponse(Wishlist wishlist) {
        wishlistId = wishlist.getWishlistId();
        name = wishlist.getGoodsName();
        count = wishlist.getCount();
    }
}
