package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Item;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.OrdersException;

@Repository
public class ItemDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Item> selectItemByOrderId(String orderId) throws OrdersException{
		String hql = "select i from Item i where i.order.id = ?";
		List<Item> items = null;
		try {
			items = hibernateTemplate.find(hql,orderId);
			if(items == null){
				throw new OrdersException("订单项不存在");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new OrdersException("查询订单失败");
		}
		return items;
	}
	
	
}
