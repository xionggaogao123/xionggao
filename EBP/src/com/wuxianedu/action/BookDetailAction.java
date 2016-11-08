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
import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;


@Namespace("/book")  
@ParentPackage(value="struts-default")
@Action(value="BookDetail")
@Results({
	@Result(name="success", location="/bookdetail.jsp"),
	@Result(name="error", location="/exception.jsp")
})
@Controller
@Scope("prototype")
public class BookDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private SelectBookService selectBookService;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BookDetailAction() {
		super();
	}


	@Override
	public String execute(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Book book = null;
		try {
			book = selectBookService.selectABook(id);
		} catch (NotFountBookException e) {
			request.setAttribute("exception", e.getMessage());
			return "error";
		}
		request.getSession().setAttribute("bookdetail", book);
		return "success";
	}
	
	



}
