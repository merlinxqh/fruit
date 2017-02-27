package com.fruit.pms.dao.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperScannerConfig {
	
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		configurer.setBasePackage("com.fruit.pms.dao.mapper");
		return configurer;
	}
	
	
}
