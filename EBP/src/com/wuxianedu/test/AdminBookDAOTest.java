package com.wuxianedu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuxianedu.dao.AdminBookDAO;
import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.QueryResult;
import com.wuxianedu.web.QueryBean;

public class AdminBookDAOTest {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/wuxianedu/config/applicationContext.xml");
		AdminBookDAO bookDAO = (AdminBookDAO) context.getBean("adminBookDAO");
		QueryBean queryBean = new QueryBean();
		queryBean.setCurrentPage(1);
		queryBean.setPageBookSize(2);
		queryBean.setStartBookIndex(0);
		try {
			QueryResult<Book> result = bookDAO.getAllBookList(queryBean);
			
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
}
