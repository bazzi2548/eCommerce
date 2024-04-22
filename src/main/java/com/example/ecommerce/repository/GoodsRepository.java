package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByCheckSoldOutFalse();
}
