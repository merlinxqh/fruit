package com.fruit.pms.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * 当前dubbo应用配置
 * @author leo
 *
 */
@Configuration
public class DubboConfig {

    /**
     * 当前应用配置
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig(){
    	ApplicationConfig applicationConfig=new ApplicationConfig();
    	applicationConfig.setName("consumer-app");
    	return applicationConfig;
    }
    
    /**
     * 服务提供者协议配置
     * @return
     */
    @Bean
    public ProtocolConfig protocol(){
    	ProtocolConfig protocol = new ProtocolConfig();
    	protocol.setName("dubbo");
    	protocol.setPort(22029);
    	protocol.setThreads(200);
    	return protocol;
    }
    
    /**
     * 连接注册中心配置
     * @return
     */
    @Bean
    public RegistryConfig registry(){
    	RegistryConfig registry=new RegistryConfig();
    	registry.setAddress("192.168.1.107:2181");
    	registry.setProtocol("zookeeper");
    	registry.setId("zookeeperService");
    	registry.setDefault(true);
    	return registry;
    }
    
    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig mc = new MonitorConfig();
        mc.setProtocol("registry");
        return mc;
    }
    
    /**
     * 提供者配置
     * @return
     */
    @Bean
    public ProviderConfig provider() {
        ProviderConfig provider = new ProviderConfig();
        provider.setMonitor(monitorConfig());
        provider.setTimeout(60000);
        provider.setDelay(0);
        provider.setRetries(1);//重试次数
        return provider;
    }
    
    @Bean
    public ConsumerConfig consumer(){
    	ConsumerConfig consumer=new ConsumerConfig();
    	consumer.setCheck(false);//是否启用检查 服务提供者是否启动 如果是true  没有提供者会报错
    	consumer.setTimeout(60000);
    	return consumer;
    }
    
   
    
    @Bean
    public AnnotationBean annotationBean(){
    	AnnotationBean annotationBean=new AnnotationBean();
    	annotationBean.setPackage("com.fruit.pms.service");
    	return annotationBean;
    }
}
