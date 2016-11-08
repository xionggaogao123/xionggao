package com.wuxianedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxianedu.dao.AdminOrder;
import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.domain.UserOrder;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.web.QueryBean;

@Service
public class AdminOrderService {
	
	@Resource
	private AdminOrder adminOrder;
	public AdminOrderService(){}
	
	public PageBean<Orders> getOrdersList(QueryBean queryBean) throws AdminException{
		QueryResult<Orders>result = adminOrder.getAllOrdersList(queryBean);
		PageBean<Orders>pageBean = new PageBean<Orders>();
		pageBean.setList(result.getList());
		pageBean.setTotalRecord(result.getTotalRecord());
		pageBean.setCurrentPage(queryBean.getCurrentPage());
		pageBean.setPageSize(queryBean.getPageOrderSize());
		return pageBean;
	}
	
	public List<Orders> getOrderById(String id){
		return adminOrder.getOrderById(id);
	}
	
}
