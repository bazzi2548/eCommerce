package com.example.ordersservice.service;

import com.example.ordersservice.client.GoodsClient;
import com.example.ordersservice.client.UserClient;
import com.example.ordersservice.domain.Wishlist;
import com.example.ordersservice.dto.request.AddWishlistRequest;
import com.example.ordersservice.dto.request.UpdateWishlistRequest;
import com.example.ordersservice.dto.response.WishlistResponse;
import com.example.ordersservice.repository.WishlistRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserClient userClient;
    private final GoodsClient goodsClient;

    public WishlistService(WishlistRepository wishlistRepository, UserClient userClient, GoodsClient goodsClient) {
        this.wishlistRepository = wishlistRepository;
        this.userClient = userClient;
        this.goodsClient = goodsClient;
    }

    public void addWishList(String token, AddWishlistRequest request) {
        Long userId = userClient.findLoginUserId(token);
        if (!goodsClient.findGoodsId(request.getGoodsId())) {
            throw new IllegalArgumentException("상품이 존재하지 않습니다");
        }
        wishlistRepository.save(new Wishlist(userId, request.getGoodsId(), request.getGoodsName(), request.getCount()));
    }

    @Transactional(readOnly = true)
    public List<WishlistResponse> getWishlist(String token) {
        Long userId = userClient.findLoginUserId(token);
        return wishlistRepository.findAllByUserId(userId)
                .stream()
                .map(WishlistResponse::new)
                .toList();
    }


    @Transactional(readOnly = true)
    public ResponseEntity<?> getWishlistGoods(long wishlistId) {
        Long goodsId = wishlistRepository.findById(wishlistId).get().getGoodsId();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/goods/" + goodsId));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public void deleteWishlist(long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public void updateWishlist(long wishlistId, UpdateWishlistRequest request) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).get();
        wishlist.updateCount(request.getCount());
    }
}
