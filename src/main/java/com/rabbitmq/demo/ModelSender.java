package com.rabbitmq.demo;

import com.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendModel(User user){
        System.out.println("Sender object : "+user.toString());
        this.amqpTemplate.convertAndSend("object",user);
    }
}
