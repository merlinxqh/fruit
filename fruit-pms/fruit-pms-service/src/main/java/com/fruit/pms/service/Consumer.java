package com.fruit.pms.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fruit.growup.api.DubboDemoService;

public class Consumer {
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:/consumer.xml"});
        context.start();
 
        DubboDemoService demoService = (DubboDemoService)context.getBean("dubboDemoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
 
        System.out.println( hello ); // 显示调用结果
    }
}
