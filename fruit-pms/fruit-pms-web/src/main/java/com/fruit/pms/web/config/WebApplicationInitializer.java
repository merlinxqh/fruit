package com.fruit.pms.web.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.fruit.pms.dao.config.DatabaseConfig;
import com.fruit.pms.dao.config.MapperScannerConfig;
import com.fruit.pms.service.config.ConsumerConfig;

/**
 * 项目启动基类
 * -- 整个项目的入口
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/**
	 * 获取配置信息
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses(){
		return new Class[] { BackConfig.class, SecurityConfig.class, DatabaseConfig.class, DubboConfig.class,
				ConsumerConfig.class, MapperScannerConfig.class };
		//		CacheConfig.class
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return null;
	}
}
