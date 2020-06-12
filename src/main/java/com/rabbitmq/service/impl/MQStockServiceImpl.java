package com.rabbitmq.service.impl;

import com.rabbitmq.config.MyRabbitMqConfig;
import com.rabbitmq.service.MQStockService;
import com.rabbitmq.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQStockServiceImpl implements MQStockService{

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private StockService stockService;

    @Override
    @RabbitListener(queues = MyRabbitMqConfig.STOCK_QUEUE)
    public void decrByStock(String stockName) {
        LOGGER.info("库存消息队列收到的消息商品信息是：{}", stockName);
        stockService.decrByStock(stockName);
    }
}
