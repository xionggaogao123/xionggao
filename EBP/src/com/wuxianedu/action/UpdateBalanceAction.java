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
import com.wuxianedu.exception.BalanceException;
import com.wuxianedu.service.UserService;

@Namespace("/book")
@ParentPackage("struts-default")
@Action("UpdateBalance")
@Controller
@Scope("prototype")
@Results({
	@Result(name="input", location="/vip/myBalance.jsp"),
	@Result(name="error", location="/exception.jsp"),
	@Result(name="success", type="redirect", location="/balanceSuccess.jsp")
})
public class UpdateBalanceAction extends ActionSupport {

	private static final long serialVersionUID = 7427834897155163615L;
	
	private String hidden;
	private String balance;
	
	@Resource
	private UserService userService;

	public UpdateBalanceAction() {
		super();
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void validate() {
		double balance = -1;
		 try {
	            balance = Double.parseDouble(this.balance);
	            if(balance < 0){
	   			 addFieldError("balance", getText("error.user.balance"));
	   		 }
	        } catch (NumberFormatException e) {
	        	addFieldError("balance", getText("error.user.balance"));
	        } catch (NullPointerException e) {
	        	addFieldError("balance", getText("error.user.balance"));
	        }
		 
		
		/*String rex = "^\\d+(\\.\\d+)?$";
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(balance);
		if (!m.find()){
			addFieldError("balance", getText("error.user.balance"));
		}*/
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
			double newBalance = Double.valueOf(balance);
			try {
				User user = (User)session.getAttribute("user");
				User updateUser = new User();
				updateUser.setId(user.getId());
				updateUser.setName(user.getName());
				updateUser.setUsername(user.getUsername());
				updateUser.setPassword(user.getPassword());
				updateUser.setRegisterTime(user.getRegisterTime());
				updateUser.setPhone(user.getPhone());
				updateUser.setGender(user.getGender());
				updateUser.setBalance(newBalance + user.getBalance());
				userService.updateBalance(updateUser);
				session.setAttribute("user", updateUser);
			} catch (BalanceException e) {
				addActionError(getText("error.user.update", new String[]{e.getMessage()}));
				return ERROR;
			}
			return SUCCESS;
		}else {
			addActionError(getText("error.user.repeat", new String[]{"正在充值，请稍后"}));
			return ERROR;
		}
	}
	
	
	
}
