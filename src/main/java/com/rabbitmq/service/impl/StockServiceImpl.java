package com.rabbitmq.service.impl;


import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.rabbitmq.entity.Stock;
import com.rabbitmq.mapper.StockMapper;
import com.rabbitmq.service.StockService;
import com.rabbitmq.zookeeper.ZooKeeperSession;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void decrByStock(String stockName) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", stockName);
        List<Stock> stocks = stockMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(stocks)) {
            Stock stock = stocks.get(0);
            stock.setStock(stock.getStock() - 1);
            stockMapper.updateById(stock);
        }
        System.out.println("生成订单成功 : " + (new Date()).toString());

    }

    @Override
    public Integer selectByExample(String stockName) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",stockName);
        List<Stock> stocks = stockMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(stocks)) {
            return stocks.get(0).getStock().intValue();
        }
        return 0;
    }
}
