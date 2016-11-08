package com.wuxianedu.action;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.AdminUserService;

@Namespace(value="/*")
@ParentPackage(value="struts-default")
@Action(value="FindUserByUserNameOrPhone")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/findUser.jsp"), 
   @Result(name="INPUT", location="/WEB-INF/admin/findUser.jsp"), 
})
@Controller
@Scope("prototype")
public class FindUserByUserNameOrPhoneAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String findUser;
	@Resource
	private AdminUserService adminUserService;

	public String getFindUser() {
		return findUser;
	}

	public void setFindUser(String findUser) {
		this.findUser = findUser;
	}
	
	@Override
	public String execute()  {
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(findUser.trim());
		Map<String,String>error = new HashMap<String, String>();
		if(isNum.matches()){ //是根据电话号码查询
			
			try {
				User user = adminUserService.getUserByPhone(findUser);
				if(user!=null){
					request.setAttribute("user", user);
					return SUCCESS;
				}else{
					return INPUT;
				}
			} catch (AdminException e) {
				error.put("userError", e.getMessage());
			}
		}else{
			
			try {
			User user = adminUserService.getUserByName(findUser);
				if(user!= null){
					request.setAttribute("user", user);
					System.out.println("user-->"+user);
					return SUCCESS;
				}else{
					return INPUT;
				}
			} catch (AdminException e) {
				error.put("userError", e.getMessage());
			}
		}
		return SUCCESS;
	}
	
}
