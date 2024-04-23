package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long userId);
}
