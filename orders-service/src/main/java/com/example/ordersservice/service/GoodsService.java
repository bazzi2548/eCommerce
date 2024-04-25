package com.example.ordersservice.service;

import com.example.ordersservice.domain.Goods;
import com.example.ordersservice.dto.request.SaveGoodsRequest;
import com.example.ordersservice.dto.response.DetailGoodsResponse;
import com.example.ordersservice.dto.response.GoodsResponse;
import com.example.ordersservice.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
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
}
