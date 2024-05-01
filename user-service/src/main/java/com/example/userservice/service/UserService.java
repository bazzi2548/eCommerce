package com.example.userservice.service;

import com.example.userservice.domain.Address;
import com.example.userservice.domain.User;
import com.example.userservice.dto.request.SaveUserRequest;
import com.example.userservice.dto.request.UpdateUserRequest;
import com.example.userservice.dto.response.MyPageResponse;
import com.example.userservice.jwt.JWTUtil;
import com.example.userservice.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUser(SaveUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return;
        }
        User user = new User(request, bCryptPasswordEncoder);
        Address address = new Address(request);
        address.setUser(user);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public MyPageResponse myPage(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());
        return new MyPageResponse(user);
    }

    public void updateUser(Authentication auth, UpdateUserRequest updateInfo) {
        User user = userRepository.findByEmail(auth.getName());
        user.update(updateInfo, bCryptPasswordEncoder);
    }
}

