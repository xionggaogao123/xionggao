package com.wuxianedu.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;

@Namespace(value="/admin")
@ParentPackage(value="struts-default")
@Action(value="AddBookPre")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/addBook.jsp"), 
})
@Controller
@Scope("prototype")
public class AddBookPreAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	
}
