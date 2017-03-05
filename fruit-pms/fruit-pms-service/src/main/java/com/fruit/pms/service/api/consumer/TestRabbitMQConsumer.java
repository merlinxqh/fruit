package com.fruit.pms.service.api.consumer;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fruit.base.framework.mq.AbstractMessageConsumer;
import com.fruit.growup.dto.GrowUpPlanDto;


@Service
public class TestRabbitMQConsumer extends AbstractMessageConsumer<GrowUpPlanDto>{


	@Override
	public void onMessage(GrowUpPlanDto message) {
		System.out.println(JSON.toJSONString(message));
		System.out.println("test mq consumer...");
	}

}
