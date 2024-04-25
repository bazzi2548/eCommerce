package com.example.goodsservice.repository;

import com.example.goodsservice.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByCheckSoldOutFalse();
}
