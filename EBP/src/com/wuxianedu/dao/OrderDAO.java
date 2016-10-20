package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Orders;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.OrdersException;

@Repository
public class OrderDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unchecked")
	public List<Orders> selectOrders(int id) throws OrdersException{
		String hql = "select ors from Orders ors where  ors.user.id= ? and ors.isDelete= false";
		List<Orders> orders = null;
		try {
			 orders = hibernateTemplate.find(hql, id);
			 if(orders == null){
				 throw new OrdersException("没有订单");
			 }
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new OrdersException("没有订单！");
		}
		return orders;
	}
	public void delOrder(String id) throws OrdersException{
		try {
			Orders or=hibernateTemplate.get(Orders.class, id);
			or.setDelete(true);
		} catch (DataAccessException e) {
			throw new OrdersException("删除失败");
		}
	}
	
	public static void main(String[] args) throws NotFountBookException, OrdersException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/wuxianedu/action/applicationContext.xml");
		System.out.println(context.getBean("hibernateTemplate"));
		OrderDAO dao=(OrderDAO) context.getBean("orderDAO");
		System.out.println(dao);
		System.out.println(dao.selectOrders(1));
		
	}
}
