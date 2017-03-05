package com.fruit.growup.xmlweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fruit.growup.service.mq.provider.TestRabbitMQProvider;

@Controller
public class LeoController {
  
	@Autowired
	private TestRabbitMQProvider provider;
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	public @ResponseBody JSONObject test(){
		JSONObject json=new JSONObject();
		json.put("status", "success");
		return json;
	}
	
	
	@RequestMapping(value="sendMsg",method=RequestMethod.GET)
	public @ResponseBody JSONObject senMsg(){
		JSONObject json=new JSONObject();
		this.provider.sentMsgToQueue();
		json.put("status", "success");
		return json;
	}
}
