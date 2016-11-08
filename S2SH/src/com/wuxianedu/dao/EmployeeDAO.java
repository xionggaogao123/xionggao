package com.wuxianedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.bean.Employee;
import com.wuxianedu.exception.EmploeeException;

@Repository
public class EmployeeDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	//1:��ѯ�����е�employeeԱ����Ϣ
	public List<Employee> getAllListEmployee() throws EmploeeException{
		List<Employee>list = null;
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department ";
		try {
			list = hibernateTemplate.find(hql);
			if(list == null || list.isEmpty()){
				throw new EmploeeException("û���ҵ���Ӧ��Ա����Ϣ");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//2 ����id��ȡ��Ա����Ϣ
	public Employee getEmployeeById(int id) throws EmploeeException{
		Employee employee = null;
		List<Employee>list = null;
		String hql = "select e from Employee e where e.id=?";
		try {
			list = hibernateTemplate.find(hql,id);
			if(list ==null ||list.isEmpty()){
				throw new EmploeeException("û���ҵ���Ϣ");
			}
			if(list.size()>1){
				throw new EmploeeException("�ظ��ļ�¼");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list.get(0);
	}
	
	//save
	public void addEmployee(Employee employee){
		hibernateTemplate.saveOrUpdate(employee);
	}
	
	//getEmployeeByLastName
	public Employee getElementByLastName(String lastName) throws EmploeeException{
		List<Employee> list = null;
		String hql = "select e from Employee e where e.lastName=?";
		try {
			list = hibernateTemplate.find(hql, lastName);
			if(list == null ||list.isEmpty()){
				throw new EmploeeException("û���ҵ���Ӧ����Ϣ");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list.get(0);
	}
	
	//delete
	public void delete(Integer id) throws EmploeeException{
		String hql = " from Employee e where e.id=?";
		List<Employee>list = null;
		try {
			list = hibernateTemplate.find(hql,id);
			if(list==null ||list.isEmpty()){
				throw new EmploeeException("û���ҵ���Ӧ����Ϣ");
			}
			hibernateTemplate.delete(list.get(0));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	
}	
