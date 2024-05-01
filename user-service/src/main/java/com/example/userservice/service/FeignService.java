package com.example.userservice.service;

import com.example.userservice.jwt.JWTUtil;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FeignService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public FeignService(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Long findLoginUserId(String token) {
        String email = jwtUtil.getEmail(token);

        return userRepository.findByEmail(email).getUserId();
    }
}
