package com.rabbitmq.demo;

import com.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Integer index){
        String context = "hello Queue " + index + " " +new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello",context);
    }
}
