package com.rabbitmq.service;

import com.rabbitmq.entity.Order;

public interface MQOrderService {

    public void createOrder(Order order);

}

