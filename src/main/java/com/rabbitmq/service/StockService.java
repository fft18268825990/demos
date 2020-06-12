package com.rabbitmq.service;

public interface StockService {

    public void decrByStock(String stockName);

    public Integer selectByExample(String stockName);

}
