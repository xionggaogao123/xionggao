package com.wuxianedu.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuxianedu.dao.SelectBookDAO;
import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.web.QueryBean;

@Service
@Transactional
public class SelectBookService {
	
	@Resource
	private SelectBookDAO selectBookDAO;
	public SelectBookService(){}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly=true)
	public List<Book> selectBookByNameAndAuthor(String some) throws NotFountBookException{
		List<Book> list=null;
		
		list=selectBookDAO.selectBookByNameAndAuthor(some);
		return list;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly=true)
	public Book selectABook(String id) throws NotFountBookException{
		Book book=null;
		int idd=Integer.parseInt(id);
		
		book=selectBookDAO.selectABook(idd);
		return book;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={NotFountBookException.class})
	public Object[] selectAllBook(QueryBean queryBean) throws NotFountBookException{
		
		QueryResult<Book> queryResult = selectBookDAO.selectAllBook(queryBean);
		
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean.setCurrentPage(queryBean.getCurrentPage());
		pageBean.setPageSize(queryBean.getPageSize());
		pageBean.setList(queryResult.getList());
		pageBean.setTotalRecord(queryResult.getTotalRecord());
		if(queryResult.getTotalRecord() == 0){
			return null;
		}
		if(pageBean.getCurrentPage() > pageBean.getTotalPage()){
			queryBean.setCurrentPage(pageBean.getTotalPage());
			queryResult = selectBookDAO.selectAllBook(queryBean);
			pageBean.setCurrentPage(queryBean.getCurrentPage());
			pageBean.setList(queryResult.getList());
		}
		// 将获得的list 集合 拆分成3,6两个集合 存入数组
		Object[] listBook = new Object[2];
		List<Book> bookThree = new ArrayList<Book>();
		List<Book> bookSix = new ArrayList<Book>();
		List<Book> list = pageBean.getList();
		for(int i = 0; i < list.size(); i++){
			if(i < 3){
				bookThree.add(list.get(i));
			}else{
				//int j = i -3;
				bookSix.add(list.get(i));
			}
		}
		listBook[0] = bookThree;
		listBook[1] = bookSix;
		return listBook;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public PageBean<Book> selectSameTypeBook(String id,QueryBean queryBean) throws NotFountBookException{
		int idd=Integer.parseInt(id);
		QueryResult<Book> queryResult = selectBookDAO.selectSameTypeBook(idd,queryBean);
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean.setCurrentPage(queryBean.getCurrentPage());
		pageBean.setPageSize(queryBean.getPageSize());
		pageBean.setList(queryResult.getList());
		pageBean.setTotalRecord(queryResult.getTotalRecord());
		if(queryResult.getTotalRecord() == 0){
			return null;
		}
		if(pageBean.getCurrentPage() > pageBean.getTotalPage()){
			queryBean.setCurrentPage(pageBean.getTotalPage());
			queryResult = selectBookDAO.selectSameTypeBook(idd,queryBean);
			pageBean.setCurrentPage(queryBean.getCurrentPage());
			pageBean.setList(queryResult.getList());
		}
		return pageBean;
	}
	
	
}
