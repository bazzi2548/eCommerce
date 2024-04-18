package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.SaveUserRequest;
import com.example.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/sign")
    public String hello(){
        return "hello";
    }
    @PostMapping("/sign")
    public String saveUser(@RequestBody SaveUserRequest request) {
        userService.saveUser(request);

        return "ok";
    }
}
