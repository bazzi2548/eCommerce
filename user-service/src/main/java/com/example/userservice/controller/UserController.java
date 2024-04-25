package com.example.userservice.controller;

import com.example.userservice.dto.request.AddWishlistRequest;
import com.example.userservice.dto.request.SaveUserRequest;
import com.example.userservice.dto.request.UpdateUserRequest;
import com.example.userservice.dto.request.UpdateWishlistRequest;
import com.example.userservice.dto.response.MyPageResponse;
import com.example.userservice.dto.response.WishlistResponse;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public String saveUser(@RequestBody SaveUserRequest request) {
        userService.saveUser(request);

        return "ok";
    }

    @GetMapping("/my-page")
    public MyPageResponse myPage(Authentication auth) {
        return userService.myPage(auth);
    }

    @PatchMapping("/my-page")
    public void updateUser(Authentication auth, @RequestBody UpdateUserRequest updateInfo) {
        userService.updateUser(auth, updateInfo);
    }

    @PostMapping("/wishlist")
    public void addWishlist(Authentication auth, @RequestBody AddWishlistRequest request) {
        userService.addWishList(auth, request);
    }

    @GetMapping("/wishlist")
    public List<WishlistResponse> getWishlist(Authentication auth) {
        return userService.getWishlist(auth);
    }

    @GetMapping("/wishlist/{id}")
    public ResponseEntity<?> getWishlistGoods(@PathVariable("id") long wishlistId) {
        return userService.getWishlistGoods(wishlistId);
    }

    @DeleteMapping("/wishlist/{id}")
    public void deleteWishlist(@PathVariable("id") long wishlistId) {
        userService.deleteWishlist(wishlistId);
    }

    @PatchMapping("/wishlist/{id}")
    public void updateWishlist(@PathVariable("id") long wishlistId, @RequestBody UpdateWishlistRequest request) {
        userService.updateWishlist(wishlistId, request);
    }



}
