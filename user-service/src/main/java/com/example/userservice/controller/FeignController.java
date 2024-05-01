package com.example.userservice.controller;

import com.example.userservice.service.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feign")
public class FeignController {
    private final FeignService feignService;

    public FeignController(FeignService feignService) {
        this.feignService = feignService;
    }

    @GetMapping("/user")
    public Long findLoginUserId(@RequestParam("token") String token) {
        return feignService.findLoginUserId(token);
    }
}
