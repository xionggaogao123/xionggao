package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Admin;
import com.wuxianedu.exception.AdminException;

@Repository
public class AdminDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public  Admin loginDao(Admin admin) throws AdminException{
		List<Admin> list=null;
		String hql = "select a from  Admin a where a.username=? and a.password=?";
		try {
			list = hibernateTemplate.find(hql, new Object[]{admin.getUsername(),admin.getPassword()});
			if(list == null || list.isEmpty()){
				throw new AdminException("用户不存在");
			}
			if(list.size()>1){
				throw new AdminException("用户记录重复");
			}
			return list.get(0);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}
		
	}
}
