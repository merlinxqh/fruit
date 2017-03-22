package com.fruit.growup.service.mq.provider;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.growup.dto.GrowUpPlanDto;

//@Service
public class TestRabbitMQProvider {
  
//	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sentMsgToQueue(){
		GrowUpPlanDto dto=new GrowUpPlanDto();
		dto.setCode("lovely baby guoguo...");
		dto.setGmtCreate(new Date());
		dto.setPlanThings("健康快乐成长....");
		//直接根据routekey发送消息 direct exchange类型 不需要指定交换机 可以使用rabbitmq默认交换机 "" 空字符串
//	    rabbitTemplate.convertAndSend("QUEST_TEST", dto);
		
		//根据exchange发送 fanout exchange类型消息 不指定queue 因为会发送到 该交换机绑定的所有队列
//		rabbitTemplate.convertAndSend("FANOUT_QUEUE_TEST_EXCHANGE",null, dto);
		
		//topic exchange类型 指定交换机name:TOPIC_QUEUE_TEST_EXCHANGE “#”表示0个或若干个关键字，“*”表示一个关键字。
//    	如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”匹配；
//    	但是“log.#”能与上述两者匹配。
		rabbitTemplate.convertAndSend("TOPIC_QUEUE_TEST_EXCHANGE", "asdfasd.TEST.abcdeft", dto);
	}
	
	
}
