package com.wuxianedu.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wuxianedu.bean.Employee;
import com.wuxianedu.dao.EmployeeDAO;
import com.wuxianedu.exception.EmploeeException;

@Service
public class EmployeeService {
	
	@Resource
	private EmployeeDAO employeeDAO;
	
	public List<Employee> getAllListEmployee() throws EmploeeException{
		return employeeDAO.getAllListEmployee();
	}
	
	public Employee getEmployeeById(int id) throws EmploeeException{
		return employeeDAO.getEmployeeById(id);
	}
	
	public void addEmployee(Employee employee){
		employeeDAO.addEmployee(employee);
	}
	
	public Employee getElementByLastName(String lastName) throws EmploeeException{
		return employeeDAO.getElementByLastName(lastName);
	}
	
	public void delete(Integer id) throws EmploeeException{
		employeeDAO.delete(id);
	}
	
}
