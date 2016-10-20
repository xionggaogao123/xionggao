package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.PayMoneyDAOException;
import com.wuxianedu.web.BookNumberBean;

@Repository
public class PayMoneyDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public void payOneBook(User user,Book book) throws PayMoneyDAOException{
		double singlePrice=user.getBalance()-book.getNewPrice();
		int totalNumber=book.getAmount()-1;
		try {
			user.setBalance(singlePrice);
			book.setAmount(totalNumber);
			hibernateTemplate.update(user);
			hibernateTemplate.update(book);
		} catch (DataAccessException e) {
			throw new PayMoneyDAOException("PayMoneyDAO--payOneBook--数据操作异常！！");
		}

	} 
	
	public void payAllBook(List<BookNumberBean> list,User user) throws PayMoneyDAOException{
		try {
			for (BookNumberBean bookBean : list) {
				Book book=hibernateTemplate.get(Book.class, bookBean.getBook().getId());
				int num=book.getAmount()-bookBean.getNumber();
				if(num>0){
					book.setAmount(num);
				}
			}
		} catch (DataAccessException e) {
			throw new PayMoneyDAOException("PayMoneyDAO--payAllBook--数据操作异常！！");
		}
		double totalMoney=0;
		for (BookNumberBean bookBean : list) {
			totalMoney=totalMoney+bookBean.getNumber()*bookBean.getBook().getNewPrice();
		}
		double total=user.getBalance()-totalMoney;
		try {
			user.setBalance(total);
			hibernateTemplate.update(user);
		} catch (DataAccessException e) {
			throw new PayMoneyDAOException("PayMoneyDAO--payAllBook-usermoney-数据操作异常！！");
		}

		

	}

	
}















