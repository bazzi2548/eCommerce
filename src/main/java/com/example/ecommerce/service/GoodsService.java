package com.example.ecommerce.service;

import com.example.ecommerce.domain.Goods;
import com.example.ecommerce.dto.request.SaveGoodsRequest;
import com.example.ecommerce.dto.response.DetailGoodsResponse;
import com.example.ecommerce.dto.response.GoodsResponse;
import com.example.ecommerce.repository.GoodsRepository;
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
