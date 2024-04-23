package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Orders;
import com.example.ecommerce.domain.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long userId);

    List<Orders> findByStatusAndCreatedAtBetween(StatusEnum status, LocalDateTime startTime, LocalDateTime endTime);

    List<Orders> findByStatusAndUpdatedAtBetween(StatusEnum status, LocalDateTime startTime, LocalDateTime endTime);

}
