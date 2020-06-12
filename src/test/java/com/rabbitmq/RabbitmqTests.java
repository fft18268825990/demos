package com.rabbitmq;

import com.rabbitmq.demo.FanoutSender;
import com.rabbitmq.demo.HelloSender;
import com.rabbitmq.demo.ModelSender;
import com.rabbitmq.demo.TopicSender;
import com.rabbitmq.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqTests {
	@Autowired
	private HelloSender helloSender;

	@Autowired
	private ModelSender modelSender;

	@Autowired
	private TopicSender topicSender;

	@Autowired
	private FanoutSender fanoutSender;

	@Test
	public void hello() throws Exception{
		for(int i =0 ; i < 10 ; i++){
			helloSender.send(i);
		}
	}

	@Test
	public void model() throws Exception{
		for(int i =0 ; i < 10 ; i++){
			modelSender.sendModel(new User());
		}
	}

	@Test
	public void topic() throws Exception{
			topicSender.send();
			topicSender.send1();
			topicSender.send2();
	}

	@Test
	public void fanout() throws Exception{
		fanoutSender.send();
	}

	@Test
	void contextLoads() {
	}
}
