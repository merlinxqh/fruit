package com.fruit.pms.service.action;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fruit.growup.api.GrowUpPlanApiService;
import com.fruit.growup.dto.GrowUpPlanDto;

@Component
public class GrowUpPlanAction {
    
	@Reference
	private GrowUpPlanApiService growUpPlanApiService;
	
	public List<GrowUpPlanDto> getDtoList(){
		return this.growUpPlanApiService.getList();
	}
}
