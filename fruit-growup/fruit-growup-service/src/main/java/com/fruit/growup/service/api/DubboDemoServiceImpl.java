package com.fruit.growup.service.api;

import com.fruit.growup.api.DubboDemoService;

public class DubboDemoServiceImpl implements DubboDemoService {

	@Override
	public String sayHello(String name) {
		
		return "hello "+name;
	}

}
