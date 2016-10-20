package com.wuxianedu.action;

import java.util.HashMap;
import java.util.Map;

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
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.AdminBookService;

@Namespace(value="/*")
@ParentPackage(value="struts-default")
@Action(value="FindBook")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/bookdetail.jsp"), 
   @Result(name="input", location="/WEB-INF/admin/bookdetail.jsp"), 
})
@Controller
@Scope("prototype")
public class FindBookAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String bookname;
	@Resource
	private AdminBookService adminBookService;
	
	public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	@Override
	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		Map<String, String>errors = new HashMap<String, String>();
		Book book;
		try {
			book = adminBookService.getBook(bookname);
			if(book!=null){
				request.setAttribute("book", book);
				return SUCCESS;
			}else{
				return INPUT;
			}
		} catch (AdminException e) {
			errors.put("bookError", e.getMessage());
		}
		return SUCCESS;
	}
}
