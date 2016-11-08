package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.User;
import com.wuxianedu.exception.BalanceException;
import com.wuxianedu.exception.UserListException;

@Repository
public class UserDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	// 查找并修改数据
	public void updateUser(User user) throws UserListException{
		try {
			hibernateTemplate.update(user);
		} catch (DataAccessException e) {
			throw new UserListException(e + "没有找到该用户");
		}
	}
	
	// 充值
	public void updateBalance(User user) throws BalanceException{
		try {
			hibernateTemplate.update(user);
		} catch (DataAccessException e) {
			throw new BalanceException("充值失败");
		}
	}
	
	// 查询余额
	public User selectBalance(User user) throws UserListException, BalanceException{
		String sql = "select u from User u where u.username = ?";
		User newUser = null;
		try {
			List<User> list = hibernateTemplate.find(sql, user.getUsername());
			if(list == null || list.isEmpty()){
				throw new UserListException("用户不存在");
			}
			if(list.size() > 1){
				throw new UserListException("用户重复记录");
			}
			newUser = list.get(0);
		} catch (DataAccessException e) {
			throw new BalanceException("查询失败");
		}
		return newUser;
		
	}
	
	public void insert(User user) throws UserListException{
		try {
			hibernateTemplate.save(user);
		} catch (DataAccessException e) {
			throw new UserListException("用户名已被注册");
		}
	}
	
	public User findUser(String username,String password) throws UserListException{
		User user = null;
		String hql = "select u from User u where u.username = ? and u.password = ?";
		try {
			List<User> list = hibernateTemplate.find(hql, username, password);
			if(list == null || list.isEmpty()){
				throw new UserListException("用户名与密码不匹配");
			}
			if(list.size() > 1){
				throw new UserListException("用户名记录重复");
			}
			user = list.get(0);
		} catch (DataAccessException e) {
			throw new UserListException(e);
 		}
		return user;
	}
	
	
	
}
