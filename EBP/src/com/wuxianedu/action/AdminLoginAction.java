package com.wuxianedu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.wuxianedu.domain.Admin;
import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.AdminBookService;
import com.wuxianedu.service.AdminService;
import com.wuxianedu.service.AdminUserService;
import com.wuxianedu.service.PageBean;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;

@Namespace(value="/*")
@ParentPackage(value="struts-default")
@Action(value="AdminLogin")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/admin_index.jsp"), 
   @Result(name="input", location="/adminLogin.jsp") 
})
@Controller
@Scope("prototype")
public class AdminLoginAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private int currentPage;
	
	@Resource
	private AdminService adminService;
	@Resource
	private AdminUserService adminUserService;
	
	@RequiredStringValidator(
        key = "error.login.username.required",
        trim = true
	)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@RequiredStringValidator(
        key = "error.login.password.required",
        trim = true
	)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		try {
			Admin findUser = adminService.loginService(admin);
			request.getSession().setAttribute("admin_username", findUser.getUsername());
			QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
			queryBean.setCurrentPage(currentPage);
			queryBean.setStartUserIndex((currentPage -1)*queryBean.getPageUserSize());
			PageBean<User>pageBean = null;
			pageBean = adminUserService.getUserList(queryBean);
			request.setAttribute("pageBean", pageBean);
		} catch (AdminException e) {
			 this.addActionError(getText("error.login.failure", new String[]{e.getMessage()}));
			 return INPUT;
		}
		return SUCCESS;
	}
	
}
