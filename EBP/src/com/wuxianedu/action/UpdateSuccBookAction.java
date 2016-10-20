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
import com.wuxianedu.service.PageBean;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;

@Namespace(value="/admin")
@ParentPackage(value="struts-default")
@Action(value="UpdateSuccBook")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/listAllbook.jsp"), 
})
@Controller
@Scope("prototype")
public class UpdateSuccBookAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String descration;
	private double oldPrice;
	private double newPrice;
	private String filename;
	private String author;
	private int amount;
	private int currentPage;
	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

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
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String execute() throws Exception {
		
		Book book = new Book(id, name,filename,descration, oldPrice, newPrice, author, amount);
		System.out.println(id+";"+filename+";"+descration+";"+oldPrice+";"+newPrice+";"+amount+";"+author);
		adminBookService.updateBook(book);
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
		queryBean.setCurrentPage(currentPage);
		queryBean.setStartBookIndex((currentPage -1)*queryBean.getPageBookSize());
		PageBean<Book>pageBean = null;
		System.out.println("queryBean--->"+queryBean);
		pageBean = adminBookService.getBookList(queryBean);
		request.setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
}
