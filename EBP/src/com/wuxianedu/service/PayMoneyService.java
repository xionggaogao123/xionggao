package com.wuxianedu.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuxianedu.dao.CreateOrderDAO;
import com.wuxianedu.dao.PayMoneyDAO;
import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.Item;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.CreateOrderException;
import com.wuxianedu.exception.PayMoneyDAOException;
import com.wuxianedu.web.BookNumberBean;


@Service
@Transactional
public class PayMoneyService {

	@Resource
	private CreateOrderDAO createOrderDAO;
	@Resource
	private PayMoneyDAO payMoneyDAO;
	
	public PayMoneyService(){}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={PayMoneyDAOException.class, CreateOrderException.class})
	public void payOne(User user,Book book) throws PayMoneyDAOException, CreateOrderException{
		payMoneyDAO.payOneBook(user, book);
		Orders or=new Orders();
		List<Item> itemList=new ArrayList<Item>();
		or.setId(createNum());
		or.setOrderNum(createNum());
		or.setCreateTime(getTime());
		or.setPrice(book.getNewPrice());
		or.setUser(user);
		or.setDelete(false);
		Item item=new Item();
		item.setBook(book);
		item.setOrder(or);
		item.setAmount(1);
		item.setPrice(book.getNewPrice());
		itemList.add(item);
		or.getItems().add(item);
		createOrderDAO.createOrd(or);
		//createOrderDAO.createItem(itemList);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={PayMoneyDAOException.class, CreateOrderException.class})
	public void payAll(List<BookNumberBean> list,User user) throws PayMoneyDAOException, CreateOrderException{
		payMoneyDAO.payAllBook(list, user);
		Orders or=new Orders();
		List<Item> itemList=new ArrayList<Item>();
		double total=0;
		or.setId(createNum());
		or.setOrderNum(createNum());
		or.setCreateTime(getTime());
		for (BookNumberBean bean :list) {
			total=total+(bean.getNumber()*bean.getBook().getNewPrice());
		}
		or.setPrice(total);
		or.setUser(user);
		or.setDelete(false);
		for (BookNumberBean bean :list) {
			Item item=new Item();
			item.setBook(bean.getBook());
			item.setOrder(or);
			item.setAmount(bean.getNumber());
			item.setPrice(bean.getNumber()*bean.getBook().getNewPrice());
			itemList.add(item);
			or.getItems().add(item);
			
		}
		createOrderDAO.createOrd(or);
		//createOrderDAO.createItem(itemList);
	}
	
	
	
	public Timestamp getTime(){
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		java.sql.Timestamp uptime=java.sql.Timestamp.valueOf(currentTime);
		return uptime;
	}
	
	public  String createNum(){
		int r1=(int)(Math.random()*(10));
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();
		String paymentID =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);
		return paymentID;
	}
	

}
