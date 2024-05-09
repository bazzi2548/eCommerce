package com.example.coreredis.executor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LuaScriptExecutor {

    private final RedisTemplate<String, Integer> redisTemplate;

    public LuaScriptExecutor(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long executeLuaScript(String luaScript, String key, int count) {
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        return redisTemplate.execute(redisScript, Collections.singletonList(key), count);
    }
}
