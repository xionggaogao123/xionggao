package com.wuxianedu.action;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;
@Namespace(value="/user")
@ParentPackage(value="struts-default")
@Action(value="Login")
@Controller
@Scope("prototype")
@Results({  
	   @Result(name="success", /*location="/index.jsp"*/type="redirect", location="/${uri}"), 
	   @Result(name="input", location="/login.jsp") 
})
public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = -9051932020609375142L;
	
	private String username;
	private String password;
	private String uri;
	private String verifyCode;
	@Resource
	private UserService userService;
	
	
	public String getUri() {
		return uri;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public void validate() {
		if(username == null || username.trim().equals("")){
			addFieldError("username", getText("error.login.username.required"));
		}else if (!username.matches("^[@a-zA-Z0-9_\u4e00-\u9fa5]{2,20}$")) {
			addFieldError("username", getText("error.userenameField.request"));
		}
		
		if(password == null || password.trim().equals("")){
			addFieldError("password", getText("error.login.password.required"));
		}else if (!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")) {
			addFieldError("password", getText("error.passwordField.request"));
			
	}
			
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		String sessionCode = (String)session.getAttribute("session_vcode");
		if(verifyCode == null || verifyCode.trim().equals("")){
			addFieldError("verifyCode", getText("error.verifyCodeField1"));
		}else if(!verifyCode.equalsIgnoreCase(sessionCode)) {
			addFieldError("verifyCode",getText("error.verifyCodeField"));
		}
		
	}
	
	@Override
	public String execute() throws Exception{
		User user = null;
		try {
			 user = userService.login(username, password);
		} catch (UserListException e) {
			this.addActionError(getText("error.login.failure"));
            return INPUT;			
		}
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute("user", user);
		uri = (String) request.getSession().getAttribute("uri");
		if(uri!=null){
			System.out.println("uri:"+uri);
		uri=uri.substring(5);
		}
		if(uri == null){
			uri = "index.jsp";
		}
		return SUCCESS;
	}
	
	
}
