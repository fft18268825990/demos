package com.rabbitmq.service;

import com.rabbitmq.entity.Order;

public interface OrderService {

    public void createOrder(Order order);

}
