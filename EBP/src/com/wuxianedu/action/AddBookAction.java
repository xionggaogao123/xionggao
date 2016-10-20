package com.wuxianedu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

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
import com.wuxianedu.domain.Category;
import com.wuxianedu.service.AdminBookService;
import com.wuxianedu.service.PageBean;
import com.wuxianedu.utils.FileUtil;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;


@Controller
@Scope("prototype")
public class AddBookAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String descration;
	private double oldPrice;
	private double newPrice;
	private String author;
	private int amount;
	private String type;
	private File filename;
	private String filenameFileName;
	private String filenameContentType; //上传文件的类型
	private String savePath;
	private String typeId;
	private int currentPage;
	
	@Resource
	private AdminBookService adminBookService;
	
	public void setSavePath(String value){
		this.savePath = value;
	}
	
	public String getSavePath() {
		String str = ServletActionContext.getServletContext().getRealPath("/bookimages");
		return str;
	}

	public int getId() {
		return id;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescration() {
		return descration;
	}
	
	public void setDescration(String descration) {
		this.descration = descration;
	}
	
	public double getOldPrice() {
		return oldPrice;
	}
	
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	
	public double getNewPrice() {
		return newPrice;
	}
	
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public File getFilename() {
		return filename;
	}

	public void setFilename(File filename) {
		this.filename = filename;
	}

	public String getFilenameFileName() {
		return filenameFileName;
	}

	public void setFilenameFileName(String filenameFileName) {
		this.filenameFileName = filenameFileName;
	}

	public String getFilenameContentType() {
		return filenameContentType;
	}

	public void setFilenameContentType(String filenameContentType) {
		this.filenameContentType = filenameContentType;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String upload() throws Exception {
		
		System.out.println("文件名为："+filenameFileName);
		System.out.println("id--->"+id+"name-->"+name+"dec-->"+descration+"myfileFileName-->"+filenameFileName+
				"file--->"+filename+"type-->"+type + ",---" + savePath + ",----" + filename.getName()+"typeId"+typeId);
		//以服务器的文件保存地址和原文件名建立上传文件的输出流
		filenameFileName = FileUtil.getFileUUId(filenameFileName);
		System.out.println("newFileName---->"+filenameFileName);
		String path = FileUtil.generateFilename(getSavePath(), filenameFileName);
		System.out.println("path--111-->"+path);
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println("contextPath----"+contextPath);
		
		
		FileOutputStream fos =  new FileOutputStream(path);
		FileInputStream fis = new FileInputStream(getFilename());
		byte []buffer = new byte[1024];
		int len = 0;
		while((len = fis.read(buffer))>0){
			fos.write(buffer,0,len);
		}
		/*
		 * private int id;
		   private String name;
			private String descration;
			private double oldPrice;
			private double newPrice;
			private String filename;
			private String author;
			private int amount;
		 * */
		
		Book book = new Book();
		book.setId(id);
		book.setAuthor(author);
		book.setAmount(amount);
		book.setDescration(descration);
		book.setName(name);
		book.setNewPrice(newPrice);
		book.setOldPrice(oldPrice);
		String realPath = path.substring(contextPath.length());
		System.out.println("realPath--->"+realPath);
		
		book.setFilename(realPath);
		Category category = new Category();
		int id = Integer.parseInt(type);
		category.setId(id);
		book.setCategory(category);
		adminBookService.addBook(book);
		
		QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
		queryBean.setCurrentPage(currentPage);
		System.out.println("currentPage--->"+currentPage);
		queryBean.setStartBookIndex((currentPage -1)*queryBean.getPageBookSize());
		PageBean<Book>pageBean = null;
		System.out.println("queryBean--->"+queryBean);
		pageBean = adminBookService.getBookList(queryBean);
		request.setAttribute("pageBean", pageBean);
		
		fos.close();
		fis.close();
		return SUCCESS;
	}
	
}
