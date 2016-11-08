package com.wuxianedu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuxianedu.dao.AdminDAO;
import com.wuxianedu.domain.Admin;
import com.wuxianedu.exception.AdminException;

@Service
public class AdminService {

	@Resource
	private AdminDAO adminDAO;
	public AdminService(){};
	public Admin loginService(Admin admin) throws AdminException{
		return adminDAO.loginDao(admin);
	}
}
