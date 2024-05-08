package com.example.coreredis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Integer> redisTemplate;

    public RedisService(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Integer readStock(String key) {
        Integer stock = redisTemplate.opsForValue().get(key);
        if (stock == null) {
            return null;
        }
        redisTemplate.expire(key, 60, TimeUnit.MINUTES);
        return stock;
    }

    public void saveStock(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.MINUTES);
    }

    public Set<String> getKeys(){
        return redisTemplate.keys("stock:*");
    }

    public Integer scheduling(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
