package com.example.userservice.domain;

import com.example.userservice.dto.request.SaveUserRequest;
import com.example.userservice.dto.request.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(insertable = false)
    private String role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList<>();

    protected User() {
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(SaveUserRequest request, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = bCryptPasswordEncoder.encode(request.getPassword());
        this.phoneNumber = request.getPhoneNumber();
    }

    public void update(UpdateUserRequest userInfo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (userInfo.getPassword() != null && !userInfo.getPassword().isEmpty()) {
            this.password = bCryptPasswordEncoder.encode(userInfo.getPassword());
        }

        if (userInfo.getPhoneNumber() != null && !userInfo.getPhoneNumber().isEmpty()) {
            this.phoneNumber = userInfo.getPhoneNumber();
        }
    }
}
