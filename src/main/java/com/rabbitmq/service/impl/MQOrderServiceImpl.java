package com.rabbitmq.service.impl;

import com.rabbitmq.config.MyRabbitMqConfig;
import com.rabbitmq.entity.Order;
import com.rabbitmq.service.MQOrderService;
import com.rabbitmq.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQOrderServiceImpl implements MQOrderService{

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Override
    @RabbitListener(queues = MyRabbitMqConfig.ORDER_QUEUE)
    public void createOrder(Order order) {

        LOGGER.info("收到订单消息，订单用户为：{}，商品名称为：{}", order.getUserName(), order.getName());

        orderService.createOrder(order);

    }
}
