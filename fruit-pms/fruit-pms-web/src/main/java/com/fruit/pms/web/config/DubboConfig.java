package com.fruit.pms.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * 当前dubbo应用配置
 * @author leo
 *
 */
@Configuration
public class DubboConfig {

	
	public static final String APPLICATION_NAME = "consumer-of-helloworld-app";

    public static final String REGISTRY_ADDRESS = "zookeeper://192.168.1.107:2181";

    public static final String ANNOTATION_PACKAGE = "com.leo.service";
    
    /**
     * 当前应用配置
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig(){
    	ApplicationConfig applicationConfig=new ApplicationConfig();
    	applicationConfig.setName(APPLICATION_NAME);
    	return applicationConfig;
    }
    
    /**
     * 连接注册中心配置
     * @return
     */
    @Bean
    public RegistryConfig registryConfig(){
    	RegistryConfig registry=new RegistryConfig();
    	registry.setAddress(REGISTRY_ADDRESS);
//    	registry.setUsername("");
//    	registry.setPassword("");
    	return registry;
    }
    
    /**
     * 服务提供者协议配置
     * @return
     */
    @Bean
    public ProtocolConfig protocol(){
    	ProtocolConfig protocol = new ProtocolConfig();
    	protocol.setName("dubbo");
    	protocol.setPort(21006);
    	protocol.setThreads(200);
    	return protocol;
    }
    
    @Bean
    public AnnotationBean annotationBean(){
    	AnnotationBean annotationBean=new AnnotationBean();
    	annotationBean.setPackage(ANNOTATION_PACKAGE);
    	return annotationBean;
    }
}
