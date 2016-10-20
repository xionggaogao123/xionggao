package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Item;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.exception.CreateOrderException;

@Repository
public class CreateOrderDAO {
	
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	public void createOrd(Orders or) throws CreateOrderException{
			try {
				hibernateTemplate.save(or);
			} catch (DataAccessException e) {
				e.printStackTrace();
				throw new CreateOrderException("CreateOrderDAO--createOrd--数据操作异常");
			}
		}
	
//	public void createItem(List<Item> itemList) throws CreateOrderException{
//		try {
//		for (Item list : itemList) {
//			hibernateTemplate.save(list);	
//		}
//		} catch (DataAccessException e) {
//			throw new CreateOrderException("CreateOrderDAO--createItem--数据操作异常");
//		}
//
//	}

}
