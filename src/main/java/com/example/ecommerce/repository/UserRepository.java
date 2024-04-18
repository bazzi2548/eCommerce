package com.example.ecommerce.repository;

import com.example.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
