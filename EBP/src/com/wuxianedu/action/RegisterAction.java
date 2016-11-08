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
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;
import com.wuxianedu.web.LoginFilter;
@Namespace(value="/user")
@ParentPackage(value="struts-default")
@Action(value="Register")
@Controller
@Scope("prototype")
@Results({  
	   @Result(name="success", location="/turn.jsp"), 
	   @Result(name="input", location="/register.jsp") 
})
public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = -1662989750117344143L;
	
	private String username;
	private String password;
	private String password2;
	private String verifyCode;
	@Resource
	private UserService userService;
	
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
	
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
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
			addFieldError("username", getText("error.register.username.required"));
		}else if (!username.matches("^[@a-zA-Z0-9_\u4e00-\u9fa5]{2,20}$")) {
			addFieldError("username", getText("error.userenameField.request"));
		}
		if(password == null || password.trim().equals("")){
			addFieldError("password", getText("error.register.password.required"));
		}else if (!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")) {
			addFieldError("password", getText("error.passwordField.request"));
		}
		if(password2 == null || password2.trim().equals("")){
			addFieldError("password2", getText("error.register.password2.required"));
		}else if (!password2.trim().equals(password)) {
			addFieldError("password2", getText("error.register.password2"));
		}	
	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		String sessionCode = (String)session.getAttribute("session_vcode");
		if(verifyCode == null || verifyCode.trim().equals("")){
			addFieldError("verifyCode", getText("error.verifyCodeField1"));
		}else if(!verifyCode.equals(sessionCode)) {
			addFieldError("verifyCode",getText("error.verifyCodeField"));
		}
	}
	 @Override
    public String execute() throws Exception{
        try {
        	userService.register(username, password);
        } catch (UserListException e) { 
        	e.printStackTrace();
            this.addActionError(getText("error.register.failure", new String[]{e.getMessage()}));
            return INPUT; 
        }
        
        ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = 
                (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
       request.getSession().setAttribute("user", new User(username,password));
        return SUCCESS;
    }
}
