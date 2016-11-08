package com.wuxianedu.action;

import java.util.List;

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
import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;

@Namespace("/book")  
@ParentPackage(value="struts-default")
@Action(value="SelectBookByNameAndAuthor")
@Results({
	@Result(name="success", location="/booklist.jsp"),
	@Result(name="error", location="/exception.jsp")
})
@Controller
@Scope("prototype")
public class SelectBookByNameAndAuthorAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private SelectBookService selectBookService;
	private String value;
	public SelectBookByNameAndAuthorAction() {
		super();
	}
	public SelectBookByNameAndAuthorAction(String value) {
		super();
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		List<Book> list=null;
		try {
			
			value=new String(value.getBytes("ISO-8859-1"),"utf-8");
			list=selectBookService.selectBookByNameAndAuthor(value);
			request.setAttribute("list", list);
		} catch (NotFountBookException e) {
			request.setAttribute("exception", e.getMessage());
			return "error";
		}

		
		return "success";
	}

}
