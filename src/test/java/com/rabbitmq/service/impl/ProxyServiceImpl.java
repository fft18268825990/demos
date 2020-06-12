package com.rabbitmq.service.impl;

import com.rabbitmq.service.ProxyService;

public class ProxyServiceImpl implements ProxyService {
    @Override
    public String hello() {
        System.out.println("hello,world@");
        return "hello";
    }
}
