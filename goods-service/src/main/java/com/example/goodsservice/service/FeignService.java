package com.example.goodsservice.service;

import com.example.goodsservice.domain.Goods;
import com.example.goodsservice.repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeignService {

    private final GoodsRepository goodsRepository;

    public FeignService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;

    }

    @Transactional(readOnly = true)
    public boolean findGoodsId(Long id) {
        Goods goods = goodsRepository.findById(id).get();

        return goods != null;
    }

    public void reduceGoods(Long id, int count) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        goods.reduceStock(count);
    }

    public void increaseGoods(Long id, int count) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        goods.increaseStock(count);
    }

    public String findGoodsName(Long id) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        return goods.getName();
    }

    public Long getPrice(Long id) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        return goods.getPrice();
    }

}
