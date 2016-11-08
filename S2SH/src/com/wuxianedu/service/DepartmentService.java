package com.wuxianedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxianedu.bean.Department;
import com.wuxianedu.dao.DepartmentDAO;

@Service
public class DepartmentService {

	@Resource
	private DepartmentDAO departmentDAO;
	
	public List<Department> getAllDepartment(){
		return departmentDAO.getAllDepartment();
	}
}
