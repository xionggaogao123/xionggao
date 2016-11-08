package com.wuxianedu.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuxianedu.dao.UserDAO;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.BalanceException;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.utils.MD5Utils;

@Service
@Transactional
public class UserService {
	
	@Resource
	private UserDAO userDAO;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={UserListException.class})
	public void updateUser(User user) throws UserListException{
		user.setPassword(MD5Utils.encode(user.getPassword()));
		userDAO.updateUser(user);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void updateBalance(User user) throws BalanceException{
		userDAO.updateBalance(user);
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={UserListException.class})
	public void register(String username, String password) throws UserListException{
		
		password = MD5Utils.encode(password);
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		java.sql.Timestamp registerTime = java.sql.Timestamp.valueOf(currentTime);
		User user = new User(username,password,registerTime);
		userDAO.insert(user);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public User login(String username,String password) throws UserListException{
		password = MD5Utils.encode(password);
		User user = userDAO.findUser(username, password);
		return user;
	}
	// 查询余额
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public User selectBalance(User user) throws UserListException, BalanceException{
		return userDAO.selectBalance(user);
	}
}
