package com.fruit.growup.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruit.growup.api.GrowUpPlanApiService;
import com.fruit.growup.dao.model.GrowUpPlan;
import com.fruit.growup.dto.GrowUpPlanDto;
import com.fruit.growup.service.GrowUpPlanService;


//@Service(proxy="javassist",version="1.0.0",timeout=5000,retries=3,interfaceClass=GrowUpPlanApiService.class)
@org.springframework.stereotype.Service
public class GrowUpPlanApiServiceImpl implements GrowUpPlanApiService{
	
	@Autowired
	private GrowUpPlanService growUpPlanService;

	@Override
	public List<GrowUpPlanDto> getList() {
		List<GrowUpPlanDto> res=new ArrayList<>();
		List<GrowUpPlan> plist=this.growUpPlanService.findByBiz(null);
		for(GrowUpPlan p:plist){
			GrowUpPlanDto dto=new GrowUpPlanDto();
			BeanUtils.copyProperties(p, dto);
			res.add(dto);
		}
		return res;
	}

}
