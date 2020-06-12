package com.rabbitmq;

import com.rabbitmq.service.RedisService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.rabbitmq.mapper"}) //扫描mapper接口
@EnableCaching
public class RabbitmqApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

	@Autowired
	RedisService redisService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		redisService.put("inventory", 1001, 20);
	}

}
