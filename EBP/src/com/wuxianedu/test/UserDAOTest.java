package com.wuxianedu.test;

import java.sql.Timestamp;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuxianedu.dao.UserDAO;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.BalanceException;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;

public class UserDAOTest {
	
	public static void main(String[] args) throws UserListException, BalanceException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/wuxianedu/config/ApplicationContext.xml");
		UserService userService = (UserService)context.getBean("userService");
		/*User userOld = new User();
		userOld.setId(3);
		userOld.setName("wang");
		userOld.setUsername("wodetian1234");
		userOld.setPassword("asdasaadadaadad123123");
		userOld.setPhone("1234567890123");
		userOld.setRegisterTime(new Timestamp(111111111l));
		userOld.setGender("m");
		userOld.setBalance(323);*/
		// User user = userService.updateUser(userOld);
		// userService.updateBalance(userOld);
		// User user = userService.selectBalance(userOld);
		// System.out.println(user);
		// userService.register("wode211", "qwe123");
		System.out.println(userService.login("wode211", "qwe123"));
	}

}
