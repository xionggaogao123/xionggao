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
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;

@Namespace("/book")
@ParentPackage("struts-default")
@Action("UpdateUser")
@Controller
@Scope("prototype")
@Results({
	@Result(name="input", location="/vip/personalCenter.jsp"),
	@Result(name="error", location="/exception.jsp"),
	@Result(name="success", type="redirect", location="/updateSuccess.jsp")
})
public class UpdateUserAction extends ActionSupport {

	private static final long serialVersionUID = -3452964305805804019L;

	private String hidden;
	private String name;
	private String username;
	private String password;
	private String repassword;
	private String phone;
	private String gender;
	
	@Resource
	private UserService userService;

	public UpdateUserAction() {
		super();
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void validate() {
		if(password == null || password.trim().equals("")){
			addFieldError("password", getText("error.passwordField.required"));
		}else{
			if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")){
				addFieldError("password", getText("error.passwordField.range", new String[]{"6", "20"}));
			}
		}
		
		if(repassword == null || repassword.trim().equals("")){
			addFieldError("repassword", getText("error.repasswordField.required"));
		}else{
			if(!repassword.equals(password)){
				addFieldError("repassword", getText("error.repasswordField.required"));
			}
		}
		
		if(phone == null || phone.trim().equals("")){
			addFieldError("phone", getText("error.phoneField.required"));
		}else{
			if(!phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$")){
				addFieldError("phone", getText("error.phoneField.range"));
			}
		}
	}
	
	@Override
	public String execute() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		String formId = (String)session.getAttribute("token");
		if(formId == null){
			formId = "";
		}
		if(hidden.equals(formId)){
			session.removeAttribute("token");
			try {
				User user = (User) session.getAttribute("user");
				User updateUser = new User();
				updateUser.setId(user.getId());
				updateUser.setName(name);
				updateUser.setUsername(user.getUsername());
				updateUser.setPassword(password);
				updateUser.setRegisterTime(user.getRegisterTime());
				updateUser.setPhone(phone);
				updateUser.setGender(gender);
				updateUser.setBalance(user.getBalance());
				userService.updateUser(updateUser);
				session.setAttribute("user", updateUser);
			} catch (UserListException e) {
				addActionError(getText("exception", new String[]{e.getMessage()}));
				return ERROR;
			}
			return SUCCESS;
		}else {
			addActionError(getText("exception", new String[]{"正在修改，请稍后..."}));
			return ERROR;
		}
	}
}
