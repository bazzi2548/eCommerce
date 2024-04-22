package com.example.ecommerce.dto.response;

import com.example.ecommerce.domain.Wishlist;
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
