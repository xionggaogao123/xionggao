package com.wuxianedu.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxianedu.dao.AdminUserDAO;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.web.QueryBean;

@Service
public class AdminUserService {
	
	@Resource
	private AdminUserDAO adminUserDAO;
	
	public AdminUserService(){}
	
	public PageBean<User> getUserList(QueryBean queryBean) throws AdminException{
		QueryResult<User> result = adminUserDAO.getAllUserList(queryBean);
		PageBean<User>pageBean = new PageBean<User>();
		pageBean.setList(result.getList());
		pageBean.setTotalRecord(result.getTotalRecord());
		pageBean.setCurrentPage(queryBean.getCurrentPage());
		pageBean.setPageSize(queryBean.getPageUserSize());
		return pageBean;
	}
	
	public User getUserByName(String name) throws AdminException{
		return adminUserDAO.getUsreByName(name);
	}
	
	public User getUserByPhone(String phone) throws AdminException{
		return adminUserDAO.getUserByPhone(phone);
	}
	
	public List<User> getUserListByMoHu(String str) throws AdminException{
		return adminUserDAO.getUserByMoHu(str);
	}
}
