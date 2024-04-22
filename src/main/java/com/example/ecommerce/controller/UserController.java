package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AddWishlistRequest;
import com.example.ecommerce.dto.request.SaveUserRequest;
import com.example.ecommerce.dto.request.UpdateUserRequest;
import com.example.ecommerce.dto.response.MyPageResponse;
import com.example.ecommerce.dto.response.WishlistResponse;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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
    public ResponseEntity<?> getWishlistGoods(@PathVariable long id) {
        return userService.getWishlistGoods(id);
    }

}
