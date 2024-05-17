package com.example.coreredis.service;

import com.example.coreredis.executor.LuaScriptExecutor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class RedisService {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final LuaScriptExecutor luaScriptExecutor;

    public RedisService(RedisTemplate<String, Integer> redisTemplate, LuaScriptExecutor luaScriptExecutor) {
        this.redisTemplate = redisTemplate;
        this.luaScriptExecutor = luaScriptExecutor;
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
        if (readStock(key) == null) {
            redisTemplate.opsForValue().set(key, value, 60, TimeUnit.MINUTES);
        }
    }

    public Set<String> getKeys(){
        Set<String> keys = new HashSet<>();
        ScanOptions options = ScanOptions.scanOptions().match("stock:*").build();
        Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(options);

        while (cursor.hasNext()) {
            keys.add(new String(cursor.next()));
        }

        return keys;
    }

    public Integer scheduling(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Long reduceStock(String key, int count) {
        // Lua 스크립트
        String luaScript =
                "local key = KEYS[1] " +
                        "local count = tonumber(ARGV[1]) " +
                        "local current_stock = tonumber(redis.call('GET', key)) " +
                        "if current_stock == nil then return nil end " +
                        "if current_stock >= count then " +
                        "local new_stock = current_stock - count " +
                        "redis.call('SET', key, new_stock) " +
                        "return new_stock " +
                        "else return -1 end";

        return luaScriptExecutor.executeLuaScript(luaScript, key, count);
    }

    public Long increaseStock(String key, int count) {
        // Lua 스크립트
        String luaScript =
                "local key = KEYS[1] " +
                        "local count = tonumber(ARGV[1]) " +
                        "local current_stock = tonumber(redis.call('GET', key)) " +
                        "if current_stock == nil then return nil end " +
                        "local new_stock = current_stock + count " +
                        "redis.call('SET', key, new_stock) " +
                        "return new_stock";

        return luaScriptExecutor.executeLuaScript(luaScript, key, count);
    }
}
