package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitMqConfig {

    //库存交换机
    public static final String STOCK_EXCHANGE = "STOCK_EXCHANGE";

    //订单交换机
    public static final String ORDER_EXCHANGE = "ORDER_EXCHANGE";

    //库存队列
    public static final String STOCK_QUEUE = "STOCK_QUEUE";

    //订单队列
    public static final String ORDER_QUEUE = "ORDER_QUEUE";

    //路由库存键
    public static final String STOCK_ROUTING_KEY = "STOCK_ROUTING_KEY";

    //路由订单键
    public static final String ORDER_ROUTING_KEY = "ORDER_ROUTING_KEY";

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //创建库存交换机
    @Bean
    public Exchange getStockExchange(){
        return ExchangeBuilder.directExchange(STOCK_EXCHANGE).durable(true).build();
    }

    //创建库存队列
    @Bean
    public Queue getStockQueue(){
        return new Queue(STOCK_QUEUE);
    }

    //绑定库存交换机和库存队列
    @Bean
    public Binding bindStock(){
        return BindingBuilder.bind(getStockQueue()).to(getStockExchange()).with(STOCK_ROUTING_KEY).noargs();
    }

    //创建订单交换机
    @Bean
    public Exchange getOrderExchange(){
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).durable(true).build();
    }

    //创建订单队列
    @Bean
    public Queue getOrderQueue(){
        return new Queue(ORDER_QUEUE);
    }

    //绑定订单交换机和订单队列
    @Bean
    public Binding bindOrder(){
        return BindingBuilder.bind(getOrderQueue()).to(getOrderExchange()).with(ORDER_ROUTING_KEY).noargs();
    }

}
