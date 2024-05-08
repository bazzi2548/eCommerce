package com.example.goodsservice.service;

import com.example.coreredis.service.RedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class SchedulerService {

    private final RedisService redisService;
    private final GoodsService goodsService;

    public SchedulerService(RedisService redisService, GoodsService goodsService) {
        this.redisService = redisService;
        this.goodsService = goodsService;
    }


    @Scheduled(cron = "0 */30 * * * *")
    public void schedule() {
        Set<String> keys = redisService.getKeys();

        for (String key : keys) {
            Integer stock = redisService.scheduling(key);
            String[] split = key.split(":");

            goodsService.updateGoods(Long.valueOf(split[1]), stock);
            System.out.println("실행 완료");
        }
    }
}
