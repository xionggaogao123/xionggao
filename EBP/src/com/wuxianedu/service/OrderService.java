package com.wuxianedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuxianedu.dao.ItemDAO;
import com.wuxianedu.dao.OrderDAO;
import com.wuxianedu.dao.SelectBookDAO;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.OrdersException;

@Service
@Transactional
public class OrderService {
	
	@Resource
	private OrderDAO orderDAO;
	@Resource
	private ItemDAO itemDAO;
	@Resource
	private SelectBookDAO selectBookDAO;
	public OrderService(){}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={OrdersException.class, NotFountBookException.class})
	public List<Orders> selectOrders(User user) throws OrdersException, NotFountBookException{
		
		// 存储Orders订单的集合
		List<Orders> orders = orderDAO.selectOrders(user.getId());
		
		return orders;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={OrdersException.class})
	public void delOrder(String id) throws OrdersException{
		orderDAO.delOrder(id);
	}
	
	

}
