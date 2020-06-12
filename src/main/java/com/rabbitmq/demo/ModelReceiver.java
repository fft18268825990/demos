package com.rabbitmq.demo;

import com.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "object")
public class ModelReceiver {
    @RabbitHandler
    public void progress(User user){
        System.out.println("Receiver Object : " + user);
    }
}
