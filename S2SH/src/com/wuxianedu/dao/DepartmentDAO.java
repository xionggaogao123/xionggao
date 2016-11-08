package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.bean.Department;

@Repository
public class DepartmentDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public List<Department> getAllDepartment(){
		String hql = "select d from Department d ";
		List<Department>list = null;
		try {
			list = hibernateTemplate.find(hql);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
}
