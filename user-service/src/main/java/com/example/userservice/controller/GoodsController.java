package com.example.userservice.controller;

import com.example.userservice.dto.request.SaveGoodsRequest;
import com.example.userservice.dto.response.DetailGoodsResponse;
import com.example.userservice.dto.response.GoodsResponse;
import com.example.userservice.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping("/goods")
    public void saveGoods(@RequestBody SaveGoodsRequest request) {
        goodsService.saveGoods(request);
    }

    @GetMapping("/goods")
    public List<GoodsResponse> findAllGoods() {
        return goodsService.findAllGoods();
    }

    @GetMapping("/goods/{id}")
    public DetailGoodsResponse findGoods(@PathVariable Long id) {
        return goodsService.findGoods(id);
    }
}
