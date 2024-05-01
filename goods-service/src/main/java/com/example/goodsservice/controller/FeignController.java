package com.example.goodsservice.controller;

import com.example.goodsservice.service.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feign")
public class FeignController {
    private final FeignService feignService;

    public FeignController(FeignService feignService) {
        this.feignService = feignService;
    }

    @GetMapping("/goodsId")
    public boolean findGoodsId(@RequestParam("id") Long id) {
        return feignService.findGoodsId(id);
    }

    @GetMapping("/reduceGoods")
    void reduceGoods(@RequestParam("id") Long id, @RequestParam("count") int count) {
        feignService.reduceGoods(id, count);
    }

    @GetMapping("/increaseGoods")
    void increaseGoods(@RequestParam("id") Long id, @RequestParam("count") int count) {
        feignService.increaseGoods(id, count);
    }

    @GetMapping("/getPrice")
    Long getPrice(@RequestParam("id") Long id) {
        System.out.println(id);
        Long price = feignService.getPrice(id);
        System.out.println("price = " + price);
        return price;
    }
}
