package com.example.goodsservice.service;

import com.example.coreredis.service.RedisService;
import com.example.goodsservice.domain.Goods;
import com.example.goodsservice.repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeignService {

    private final GoodsRepository goodsRepository;
    private final RedisService redisService;

    public FeignService(GoodsRepository goodsRepository, RedisService redisService) {
        this.goodsRepository = goodsRepository;
        this.redisService = redisService;
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

    public void uploadStock(Long id) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        String s = "stock:"+id;
        System.out.println("s :" + s);
        redisService.saveStock(s, goods.getStock());
    }
}
