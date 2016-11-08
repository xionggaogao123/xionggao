package com.wuxianedu.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/wuxianedu/config/applicationContext.xml");
		System.out.println("sessionFactory--->"+ context.getBean("sessionFactory"));
		System.out.println("hibernateTemplate--->"+context.getBean("hibernateTemplate"));
	}
}
