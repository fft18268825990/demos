package com.rabbitmq.service.impl;

import com.rabbitmq.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Component
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void put(String key,Object value,long millis){
        redisTemplate.opsForValue().set(key,value,millis,TimeUnit.MINUTES);
    }

    public void putForHash(String objectKey,String hKey,String hValue){
        redisTemplate.opsForHash().put(objectKey,hKey,hValue);
    }

    public <T> T get(String key,Class<T> type){
        return (T) redisTemplate.boundValueOps(key).get();
    }

    public boolean expire(String key, long millis) {
        return redisTemplate.expire(key, millis, TimeUnit.MILLISECONDS);
    }

    public boolean persist(String key) {
        return redisTemplate.hasKey(key);
    }

    public String getString(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) redisTemplate.opsForValue().get(key);
    }

    public Long getLong(String key) {
        return (Long) redisTemplate.opsForValue().get(key);
    }

    public Date getDate(String key) {
        return (Date) redisTemplate.opsForValue().get(key);
    }

    /**
     * 对指定key的键值减一
     *
     * @param key
     * @return
     */
    public Long decrBy(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }
    
}
