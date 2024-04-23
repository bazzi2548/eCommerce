package com.example.ecommerce.repository;

import com.example.ecommerce.domain.OrdersGoods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGoodsRepository extends JpaRepository<OrdersGoods, Long> {
}
