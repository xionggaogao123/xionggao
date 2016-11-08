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
import com.wuxianedu.service.AdminBookService;

@Namespace(value="/*")
@ParentPackage(value="struts-default")
@Action(value="UpdateBook")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/updateBook.jsp"), 
})
@Controller
@Scope("prototype")
public class UpdateBookAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private double newPrice;
	private double oldPrice;
	private int amount;
	private String name;
	private String author;
	private String descration;
	@Resource
	private AdminBookService adminBookService;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescration() {
		return descration;
	}
	public void setDescration(String descration) {
		this.descration = descration;
	}
	
	@Override
	public String execute() throws Exception {
		Book book = adminBookService.getBookInfoById(id);
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute("book", book);
		return SUCCESS;
	}
}
