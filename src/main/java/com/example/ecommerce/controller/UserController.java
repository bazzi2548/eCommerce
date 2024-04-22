package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.SaveUserRequest;
import com.example.ecommerce.dto.request.UpdateUserRequest;
import com.example.ecommerce.dto.response.MyPageResponse;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

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

    @GetMapping("/")
    public String mainP() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main page: " + name + role;
    }

    @GetMapping("/my-page")
    public MyPageResponse myPage(Authentication auth) {
        return userService.myPage(auth);
    }

    @PatchMapping("/my-page")
    public void updateUser(Authentication auth, @RequestBody UpdateUserRequest updateInfo) {
        userService.updateUser(auth, updateInfo);
    }
}
