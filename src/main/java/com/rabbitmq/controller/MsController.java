package com.rabbitmq.controller;

import com.rabbitmq.config.MyRabbitMqConfig;
import com.rabbitmq.entity.Order;
import com.rabbitmq.service.*;
import com.rabbitmq.zookeeper.DistributedLockByCurator;
import com.rabbitmq.zookeeper.ZooKeeperSession;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
public class MsController {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisService redisService;

    @Autowired
    StockService stockService;

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ZooKeeperSession zooKeeperSession;

    @Autowired
    DistributedLockByCurator distributedLockByCurator;
    /**
      * 使用Redis+消息队列进行秒杀实现
      *
      */
    @RequestMapping("/MsRedisRabbitmq")
    @ResponseBody
    public String MsRedisRabbitmq(@RequestParam(value = "userName")String userName,@RequestParam("stockName") String stockName){

        LOGGER.info("参加秒杀的用户是：{}，秒杀的商品是：{}", userName, stockName);
        String message = null;

        //调用redis给相应的商品库存量减一
        Long decrByResult = redisService.decrBy(stockName);

        if (decrByResult >= 0) {

            LOGGER.info("用户：{}秒杀该商品：{}库存有余，可以进行下订单操作", userName, stockName);

            //发送信息给库存队列，将数据库库存存减一
            rabbitTemplate.convertAndSend(MyRabbitMqConfig.STOCK_EXCHANGE, MyRabbitMqConfig.STOCK_ROUTING_KEY, stockName);

            Order order = new Order();
            order.setName(stockName);
            order.setUserName(userName);

            //发送信息给订单队列
            rabbitTemplate.convertAndSend(MyRabbitMqConfig.ORDER_EXCHANGE, MyRabbitMqConfig.ORDER_ROUTING_KEY, order);

            message = "用户" + userName + "秒杀" + stockName + "成功";

        } else {
            LOGGER.info("用户：{}秒杀时商品的库存量没有剩余,秒杀结束", userName);
            message = userName + "商品的库存量没有剩余,秒杀结束";
        }


        return message;

    }

    /**
     *  基于原生Zookeeper纯数据库秒杀实现
     *
     */
    @RequestMapping("/MsDataBaseByCurator")
    @ResponseBody
    public String MsDataBaseByZk(@RequestParam(value = "userName")String userName,@RequestParam("stockName") String stockName){

        LOGGER.info("参加秒杀的用户是：{}，秒杀的商品是：{}", userName, stockName);
        String message = null;

        Long adId = 1L;
        try {
            distributedLockByCurator.acquireDistributedLock("lock"+adId);

            //查询库存
            Integer stockCount = stockService.selectByExample(stockName);

            if (stockCount > 0){

                LOGGER.info("用户：{}秒杀该商品：{}库存有余，可以进行下订单操作", userName, stockName);
                stockService.decrByStock(stockName);

                Order order = new Order();
                order.setName(stockName);
                order.setUserName(userName);

                orderService.createOrder(order);

                message = "用户" + userName + "秒杀" + stockName + "成功";
            }else{
                LOGGER.info("用户：{}秒杀时商品的库存量没有剩余,秒杀结束", userName);
                message = userName + "商品的库存量没有剩余,秒杀结束";
            }
        }finally {
            distributedLockByCurator.releaseDistributedLock("lock"+adId);
        }
        return message;
    }

    /**
     *  基于原生Zookeeper纯数据库秒杀实现
     *
     */
    @RequestMapping("/MsDataBaseByZk")
    @ResponseBody
    public String MsDataBaseByCurator(@RequestParam(value = "userName")String userName,@RequestParam("stockName") String stockName){

        LOGGER.info("参加秒杀的用户是：{}，秒杀的商品是：{}", userName, stockName);
        String message = null;

        Long adId = 1L;
        try {
            zooKeeperSession = ZooKeeperSession.getInstance();
            zooKeeperSession.acquireDistributedLock(adId);

            //查询库存
            Integer stockCount = stockService.selectByExample(stockName);

            if (stockCount > 0){

                LOGGER.info("用户：{}秒杀该商品：{}库存有余，可以进行下订单操作", userName, stockName);
                stockService.decrByStock(stockName);

                Order order = new Order();
                order.setName(stockName);
                order.setUserName(userName);

                orderService.createOrder(order);

                message = "用户" + userName + "秒杀" + stockName + "成功";
            }else{
                LOGGER.info("用户：{}秒杀时商品的库存量没有剩余,秒杀结束", userName);
                message = userName + "商品的库存量没有剩余,秒杀结束";
            }
        }finally {
            zooKeeperSession.releaseDistributedLock(adId);
        }
        return message;
    }
}
