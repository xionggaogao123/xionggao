package com.wuxianedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuxianedu.dao.AdminBookDAO;
import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.Category;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.web.QueryBean;

@Transactional
@Service
public class AdminBookService {
	
	@Resource
	private AdminBookDAO adminBookDAO;
	public AdminBookService(){}
	
	public PageBean<Book> getBookList(QueryBean queryBean) throws AdminException{
		QueryResult<Book>result = adminBookDAO.getAllBookList(queryBean);
		PageBean<Book>pageBean = new PageBean<Book>();
		pageBean.setList(result.getList());
		pageBean.setTotalRecord(result.getTotalRecord());
		pageBean.setCurrentPage(queryBean.getCurrentPage());
		pageBean.setPageSize(queryBean.getPageBookSize());
		return pageBean;
	}
	
	public Book getBook(String name) throws AdminException{
		return adminBookDAO.getBook(name);
	}
	
	public Book getBookInfoById(int id) throws AdminException{
		return adminBookDAO.getBookInfoById(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateBook(Book book) throws AdminException{
		int id = book.getId();
		Book b = adminBookDAO.getBookInfoById(id);
		b.setAmount(book.getAmount());
		b.setAuthor(book.getAuthor());
		b.setDescration(book.getDescration());
		b.setFilename(book.getFilename());
		b.setId(book.getId());
		b.setName(book.getName());
		b.setNewPrice(book.getNewPrice());
		b.setOldPrice(book.getOldPrice());
		adminBookDAO.updateBook(b);
	}
	
	public void addBook(Book book) throws AdminException{
		adminBookDAO.addBook(book);
	}
	public List<Category> getCategories(){
		return adminBookDAO.getCategory();
	}
	
	public List<Category> getCategoriesById(int id){
		return adminBookDAO.getCategoriesByParentId(id);
	}
	
	public List<Book> getBookByMoHu(String str){
		return adminBookDAO.getBookByMoHu(str);
	}
}
