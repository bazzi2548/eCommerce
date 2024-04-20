package com.example.ecommerce.service;

import com.example.ecommerce.domain.Address;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.dto.request.SaveUserRequest;
import com.example.ecommerce.dto.request.UpdateUserRequest;
import com.example.ecommerce.dto.response.MyPageResponse;
import com.example.ecommerce.jwt.JWTUtil;
import com.example.ecommerce.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void saveUser(SaveUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ;
        }
        User user = new User(request, bCryptPasswordEncoder);
        Address address = new Address(request);
        address.setUser(user);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public MyPageResponse myPage(HttpServletRequest request) {
        String access = request.getHeader("access");
        String email = jwtUtil.getEmail(access);
        User user = userRepository.findByEmail(email);

        return new MyPageResponse(user);
    }


    public void updateUser(HttpServletRequest request, UpdateUserRequest updateInfo) {
        String access = request.getHeader("access");
        String email = jwtUtil.getEmail(access);
        User user = userRepository.findByEmail(email);

        user.update(updateInfo);
    }
}

