package com.fruit.growup.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fruit.base.framework.utils.IDGeneratorUtil;
import com.fruit.growup.api.GrowUpPlanApiService;
import com.fruit.growup.dao.model.GrowUpPlan;
import com.fruit.growup.service.GrowUpPlanService;

@Controller
public class GrowupController {
   
	@Autowired
	private GrowUpPlanService growUpPlanService;
	
	@Autowired
	private GrowUpPlanApiService growUpPlanApiService;
	
	@RequestMapping("test")
	public @ResponseBody JSONObject test(){
		GrowUpPlan plan=new GrowUpPlan();
		plan.setCode(IDGeneratorUtil.getCode());
		plan.setGmtCreate(new Date());
		plan.setGmtModified(new Date());
		plan.setPlanThings("让她有一种持久的爱好...");
		plan.setPlanDate(new Date());
		plan.setStatus(1);
		this.growUpPlanService.add(plan);
		return new JSONObject();
	}
	
	@RequestMapping("ttt")
	public @ResponseBody Object ttt(){
		return this.growUpPlanApiService.getList();
	}
}
