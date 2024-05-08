package com.example.goodsservice.service;

import com.example.coreredis.service.RedisService;
import com.example.goodsservice.domain.Goods;
import com.example.goodsservice.dto.request.SaveGoodsRequest;
import com.example.goodsservice.dto.response.DetailGoodsResponse;
import com.example.goodsservice.dto.response.GoodsResponse;
import com.example.goodsservice.dto.response.RemainGoodsResponse;
import com.example.goodsservice.repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final RedisService redisService;

    public GoodsService(GoodsRepository goodsRepository, RedisService redisService) {
        this.goodsRepository = goodsRepository;
        this.redisService = redisService;
    }

    public void saveGoods(SaveGoodsRequest request) {
        goodsRepository.save(new Goods(request));
    }


    public List<GoodsResponse> findAllGoods() {
        return goodsRepository.findAllByCheckSoldOutFalse()
                .stream()
                .map(GoodsResponse::new)
                .toList();
    }

    public DetailGoodsResponse findGoods(Long id) {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("goods doesn't exist"));

        return new DetailGoodsResponse(goods);
    }

    public RemainGoodsResponse findRemain(Long id) {
        String key = "stock:" + id;
        Integer stock = redisService.readStock(key);
        if (stock == null) {
            System.out.println("hello");
            Goods goods = goodsRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("goods doesn't exist"));
            stock = goods.getStock();
            redisService.saveStock(key, stock);
        }

        return new RemainGoodsResponse(id, stock);
    }

    public void updateGoods(Long id, int count) {
        Goods goods = goodsRepository.findById(id).get();
        goods.setStock(count);
    }
}
