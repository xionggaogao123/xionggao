package com.wuxianedu.action;

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

@Namespace("/user")
@ParentPackage("struts-default")
@Action("Exit")
@Controller
@Scope("singlton")
@Results({
	@Result(name="input", location="/index.jsp")
})
public class ExitAction extends ActionSupport{

	private static final long serialVersionUID = 8153093567916570578L;

	@Override
	public String execute(){
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().removeAttribute("user");
		
		return INPUT;
		
		
	}
	
	
		
		
		
	
}
