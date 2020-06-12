package com.rabbitmq.service;


import java.util.Date;

public interface RedisService {

    public void put(String key,Object value,long millis);

    public void putForHash(String objectKey,String hKey,String hValue);

    public <T> T get(String key,Class<T> type);

    public boolean expire(String key, long millis);

    public boolean persist(String key);

    public String getString(String key);

    public Integer getInteger(String key);

    public Long getLong(String key);

    public Date getDate(String key);

    public Long decrBy(String key);

}
