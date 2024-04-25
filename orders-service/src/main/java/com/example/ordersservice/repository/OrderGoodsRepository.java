package com.example.ordersservice.repository;

import com.example.ordersservice.domain.OrdersGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderGoodsRepository extends JpaRepository<OrdersGoods, Long> {
    List<OrdersGoods> findByOrdersId(Long ordersId);
}
