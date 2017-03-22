package com.fruit.pms.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.fruit.growup.api.GrowUpPlanApiService;

@Configuration
public class ConsumerConfig{

	@Bean
    public ReferenceBean<GrowUpPlanApiService> growUpPlanApiService() {
        ReferenceBean<GrowUpPlanApiService> ref = new ReferenceBean<GrowUpPlanApiService>();
        ref.setVersion("1.0.0");
        ref.setInterface(GrowUpPlanApiService.class);
        ref.setTimeout(5000);
        ref.setRetries(3);
        ref.setCheck(false);
        return ref;
    }
    
}