package com.example.ecommerce.repository;

import com.example.ecommerce.domain.OrdersGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderGoodsRepository extends JpaRepository<OrdersGoods, Long> {
    List<OrdersGoods> findByOrdersId(Long ordersId);
}
