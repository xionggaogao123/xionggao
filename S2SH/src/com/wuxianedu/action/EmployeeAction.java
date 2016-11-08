package com.wuxianedu.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.wuxianedu.bean.Department;
import com.wuxianedu.bean.Employee;
import com.wuxianedu.exception.EmploeeException;
import com.wuxianedu.service.DepartmentService;
import com.wuxianedu.service.EmployeeService;

@Namespace("/*")
@ParentPackage("struts-default")
@InterceptorRefs(@InterceptorRef("modelDriven")) 
@Controller
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>,Preparable{
	
	private Integer id;
	private InputStream inputStream;
	private Employee employee;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	private static final long serialVersionUID = 1L;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	
	@Action(value="getAllEmployee",results={
			@Result(name="success",location="/WEB-INF/view/list_employee.jsp"),
			@Result(name="input",location="/index.jsp")
	})
	public String getAllListEmployee(){
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			List<Employee>list= employeeService.getAllListEmployee();
			request.setAttribute("list", list);
			System.out.println("list--->"+list);
		} catch (EmploeeException e) {
			request.setAttribute("error",e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	/*
	 * strut2接受ajax的请求，处理方式有二种，一种是最原始的方法，out.print();一种是inputStream
	 * 注意要配置二个param参数
	 * 注意:这里的Result里面的type一定是stream
	 * 
	 */
	@Action(value="deleteEmployee",results={
			@Result(name="success",type="stream",
					location="${pageContext.request.contextPath }/getAllEmployee.action",
					params={"contentType","text/html","inputName","inputStream"})
	})
	public String deleteEmployee() throws IOException{
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		} catch (Exception e) {
			inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
		} 
		return SUCCESS;
	}
	
	//得到所有的部门
	@Action(value="addEmployee",results={
			
			@Result(name="success",location="/WEB-INF/view/addemployee.jsp")
	})
	
	public String addEmployee(){
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		List<Department>list = departmentService.getAllDepartment();
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	@Action(value="savaEmployee",results={
			@Result(name="success",location="/index.jsp")
	})

	public String savaEmployee(){
		System.out.println("employee-->"+employee);
		
		return SUCCESS;
	}
	
	@Override
	public Employee getModel() {
		if(employee==null){
			return new Employee();
		}
		return employee;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
