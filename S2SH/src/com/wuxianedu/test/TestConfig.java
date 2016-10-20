package com.wuxianedu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfig {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/wuxianedu/config/applicationContext.xml");
		System.out.println("context-->"+context);
		System.out.println("dataSource-->"+context.getBean("dataSource"));
		System.out.println("sessionFactory-->"+context.getBean("sessionFactory"));
		System.out.println("hibernateTemplate-->"+context.getBean("hibernateTemplate"));
	}
}	
