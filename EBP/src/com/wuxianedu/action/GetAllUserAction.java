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
import com.wuxianedu.domain.User;
import com.wuxianedu.service.AdminUserService;
import com.wuxianedu.service.PageBean;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;


@Namespace(value="/admin")
@ParentPackage(value="struts-default")
@Action(value="GetAllUser")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/admin_listuser.jsp"), 
  
})
@Controller
@Scope("prototype")
public class GetAllUserAction extends ActionSupport{

	private int currentPage;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	

	@Resource
	private AdminUserService adminUserService;
	
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		
		QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
		queryBean.setCurrentPage(currentPage);
		queryBean.setStartUserIndex((currentPage -1)*queryBean.getPageUserSize());
		PageBean<User>pageBean = null;
		pageBean = adminUserService.getUserList(queryBean);
		request.setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	
}
