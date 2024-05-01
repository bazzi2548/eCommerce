package com.example.ordersservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "goods-service", url = "http://localhost:8082")
public interface GoodsClient {

    @GetMapping("/api/feign/goodsId")
    boolean findGoodsId(@RequestParam("id") Long id);

    @GetMapping("/api/feign/reduceGoods")
    void reduceGoods(@RequestParam("id") Long id, @RequestParam("count") int count);

    @GetMapping("/api/feign/increaseGoods")
    void increaseGoods(@RequestParam("id") Long id, @RequestParam("count") int count);

    @GetMapping("/api/feign/getPrice")
    Long getPrice(@RequestParam("id") Long id);
}
