package com.fruit.growup.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.fruit.growup.api.GrowUpPlanApiService;

@Configuration
public class ExportServiceConfig{

	@Bean
    public ServiceBean<GrowUpPlanApiService> userServiceExport(GrowUpPlanApiService growUpPlanApiService) {
        ServiceBean<GrowUpPlanApiService> serviceBean = new ServiceBean<GrowUpPlanApiService>();
        serviceBean.setProxy("javassist");
        serviceBean.setVersion("1.0.0");
        serviceBean.setInterface(GrowUpPlanApiService.class.getName());
        serviceBean.setTimeout(5000);
        serviceBean.setRetries(3);
        serviceBean.setRef(growUpPlanApiService);
        return serviceBean;
    }
	
}