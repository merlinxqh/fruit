package com.fruit.growup.web.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.fruit.growup.dao.config.DatabaseConfig;
import com.fruit.growup.dao.config.MapperScannerConfig;
import com.fruit.growup.service.config.ExportServiceConfig;

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
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {BackConfig.class,SecurityConfig.class,DatabaseConfig.class, MapperScannerConfig.class, 
				DubboConfig.class, ExportServiceConfig.class};
//		, CacheConfig.class ,
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
