package com.wuxianedu.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuxianedu.bean.Employee;
import com.wuxianedu.dao.EmployeeDAO;
import com.wuxianedu.exception.EmploeeException;

public class TestEmployeeDAO {
	public static void main(String[] args) throws EmploeeException {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/wuxianedu/config/applicationContext.xml");
		EmployeeDAO employeeDAO = (EmployeeDAO)context.getBean("employeeDAO");
		/*List<Employee>list = employeeDAO.getAllListEmployee();
		System.out.println(list);*/
		
		employeeDAO.delete(1);
	}
	
}	
