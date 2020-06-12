package com.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;//操作k-v对象的

    @Test
    public void test01() {
        //stringRedisTemplate.opsForValue().append("key","helloword");
        //String msg = stringRedisTemplate.opsForValue().get("key");
        //System.out.println("msg:"+msg);
        stringRedisTemplate.opsForList().leftPush("firstList","1");
        stringRedisTemplate.opsForList().leftPush("firstList","2");
    }
}
