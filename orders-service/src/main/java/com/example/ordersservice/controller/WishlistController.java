package com.example.ordersservice.controller;

import com.example.ordersservice.dto.request.AddWishlistRequest;
import com.example.ordersservice.dto.request.UpdateWishlistRequest;
import com.example.ordersservice.dto.response.WishlistResponse;
import com.example.ordersservice.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/wishlist")
    public void addWishlist(@CookieValue("access") String token, @RequestBody AddWishlistRequest request) {
        wishlistService.addWishList(token, request);
    }

    @GetMapping("/wishlist")
    public List<WishlistResponse> getWishlist(@CookieValue("access") String token) {
        return wishlistService.getWishlist(token);
    }

    @GetMapping("/wishlist/{id}")
    public ResponseEntity<?> getWishlistGoods(@PathVariable("id") long wishlistId) {
        return wishlistService.getWishlistGoods(wishlistId);
    }

    @DeleteMapping("/wishlist/{id}")
    public void deleteWishlist(@PathVariable("id") long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }

    @PatchMapping("/wishlist/{id}")
    public void updateWishlist(@PathVariable("id") long wishlistId, @RequestBody UpdateWishlistRequest request) {
        wishlistService.updateWishlist(wishlistId, request);
    }
}
