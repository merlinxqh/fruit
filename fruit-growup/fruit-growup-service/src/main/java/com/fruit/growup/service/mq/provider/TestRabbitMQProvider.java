package com.fruit.growup.service.mq.provider;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.growup.dto.GrowUpPlanDto;

@Service
public class TestRabbitMQProvider {
  
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sentMsgToQueue(){
		GrowUpPlanDto dto=new GrowUpPlanDto();
		dto.setCode("lovely baby guoguo...");
		dto.setGmtCreate(new Date());
		dto.setPlanThings("健康快乐成长....");
		rabbitTemplate.convertAndSend("QUEUE_TEST", dto);
	}
	
	
}
