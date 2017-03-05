package com.fruit.pms.xmlweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fruit.growup.api.GrowUpPlanApiService;
import com.fruit.pms.dao.model.Resource;
import com.fruit.pms.service.ResourceService;

@Controller
public class LeoController {
   
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private GrowUpPlanApiService growupApiService;
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	public @ResponseBody List<Resource> test(){
		System.out.println(".......success......");
		System.out.println(JSON.toJSONString(growupApiService.getList()));
		return resourceService.findByBiz(null);
	}
}
