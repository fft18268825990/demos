package com.rabbitmq;

import com.rabbitmq.service.StockService;
import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZookeeperTests {

    @Autowired
    StockService stockService;

    @Autowired
    private CuratorFramework curatorFramework;

    @Test
    public void test01() {
        System.out.println("##生成了订单####");
        for(int i=0;i<50;i++){

        }
    }

    @Test
    public void test02(){
        try {
            curatorFramework = curatorFramework.usingNamespace("lock-namespace");
            System.out.println(curatorFramework.checkExists().forPath("/lock"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
