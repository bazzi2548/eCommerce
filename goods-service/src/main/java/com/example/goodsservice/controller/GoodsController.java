package com.example.goodsservice.controller;


import com.example.goodsservice.client.UserClient;
import com.example.goodsservice.dto.request.SaveGoodsRequest;
import com.example.goodsservice.dto.response.DetailGoodsResponse;
import com.example.goodsservice.dto.response.GoodsResponse;
import com.example.goodsservice.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final UserClient userClient;

    public GoodsController(GoodsService goodsService, UserClient userClient) {
        this.goodsService = goodsService;
        this.userClient = userClient;
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
