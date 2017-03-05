package com.fruit.pms.service.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fruit.growup.api.GrowUpPlanApiService;
import com.fruit.growup.dto.GrowUpPlanDto;

@Component
public class GrowUpPlanAction {
    
	@Autowired
	private GrowUpPlanApiService growUpPlanApiService;
	
	public List<GrowUpPlanDto> getDtoList(){
		return this.growUpPlanApiService.getList();
	}
}
