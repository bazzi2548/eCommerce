package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
