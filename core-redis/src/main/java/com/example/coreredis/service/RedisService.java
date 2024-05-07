package com.example.coreredis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Integer> redisTemplate;

    public RedisService(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Integer readStock(String key) {
        System.out.println("key = " + key);
        return redisTemplate.opsForValue().get(key);
    }

    public void saveStock(String key, Integer value) {
        System.out.println("key: " + key + " value: " + value);

        redisTemplate.opsForValue().set(key, value);
    }

}
