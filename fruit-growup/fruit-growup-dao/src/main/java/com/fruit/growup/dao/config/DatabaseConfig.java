package com.fruit.growup.dao.config;

import java.sql.SQLException;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author leo
 *
 */

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	@Value("${jdbc.driverClass}")
	private String jdbcDriver;
	
	@Value("${jdbc.url}")
	private String dbUrl;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	/**
	 * 初始化连接池大小
	 */
	@Value("${druid.initialSize}")
	private int initialSize;
	
	/**
	 * 最大连接数
	 */
	@Value("${druid.maxActive}")
	private int maxActive;
	
	/**
	 * 连接池最小空闲
	 */
	@Value("${druid.minIdle}")
	private int minIdle;
	
	/**
	 * 获取连接最大等待时间
	 */
	@Value("${druid.maxWait}")
	private long maxWait;
	@Value("${druid.validationQuery}")
	private String validationQuery;
	@Value("${druid.testOnBorrow}")
	private boolean testOnBorrow;
	
	@Bean(name="dataSource")
	public DruidDataSource dataSource(){
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		/**
		 * 配置间隔多久才需要检测, 检测需要关闭的空闲连接  单位 毫秒
		 */
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		/**
		 * 配置一个连接在池中最小生存的时间，单位是毫秒
		 */
		dataSource.setMinEvictableIdleTimeMillis(25200000);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(1800);
		dataSource.setLogAbandoned(true);
		try {
			dataSource.setFilters("mergeStat");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dataSource;
	}
	
    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        /**
         * mybatis配置
         */
        Resource resource=new ClassPathResource("mybatis-config.xml");
        sessionFactory.setConfigLocation(resource);
        return sessionFactory.getObject();
    }
    
    
    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
    	SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory(),ExecutorType.SIMPLE);
    	return template;
    }
    
//  @Bean(name="dataSourcejdbc")
//  public DynamicRoutingDataSource dataSourcejdbc(){
//  	DynamicRoutingDataSource dynamic=new DynamicRoutingDataSource();
//  	DruidDataSource defaultDataSource=dataSource();
//  	defaultDataSource.setUrl("jdbc:mysql://localhost:3306/leo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
//  	//设置默认 数据源
//  	dynamic.setDefaultTargetDataSource(defaultDataSource);
//  	
//  	Map<Object, Object> targetDataSources=new HashMap<Object, Object>();//多数据源map
//  	
//  	DruidDataSource adminDataSource=dataSource();
//  	adminDataSource.setUrl("jdbc:mysql://localhost:3306/leo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
//  	targetDataSources.put(DynamicRoutingDataSource.MASTER_DATA_SOURCE, adminDataSource);
//  	
//  	dynamic.setTargetDataSources(targetDataSources);
//  	return dynamic;
//  }

}
