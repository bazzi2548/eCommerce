package com.example.userservice.controller;

import com.example.userservice.dto.request.SaveUserRequest;
import com.example.userservice.dto.request.UpdateUserRequest;
import com.example.userservice.dto.response.MyPageResponse;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/user/my-page")
    public MyPageResponse myPage(Authentication auth) {
        return userService.myPage(auth);
    }

    @PatchMapping("/user/my-page")
    public void updateUser(Authentication auth, @RequestBody UpdateUserRequest updateInfo) {
        userService.updateUser(auth, updateInfo);
    }
}
