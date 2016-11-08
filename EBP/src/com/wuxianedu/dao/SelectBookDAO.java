package com.wuxianedu.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.QueryResult;
import com.wuxianedu.web.QueryBean;

@Repository
public class SelectBookDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public Book selectABook(int id) throws NotFountBookException{
		Book book=null;
		try {
			book=hibernateTemplate.get(Book.class, id);
			if(book == null){
				throw new NotFountBookException("没有找到这本书");
			}
		} catch (DataAccessException e) {
			throw new NotFountBookException("没有找到这本书");
		}
		return book;
	} 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public QueryResult<Book> selectAllBook(QueryBean queryBean) throws NotFountBookException{
		QueryResult<Book> queryResult = null;
		List<Book> list = null;
		List<Long> array = null;
		final String hql = "select b from Book b";
		String hql2 = "select count(b) from Book b";
		final int page=queryBean.getStartIndex();
		final int maxLines=queryBean.getPageSize();
		try {
			list = hibernateTemplate.executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) 
					throws HibernateException, SQLException{
					Query query = session.createQuery(hql);
					query.setFirstResult(page * maxLines);
					query.setMaxResults(maxLines);
					return query.list();
				}
			});
			array = hibernateTemplate.find(hql2);
			queryResult = new QueryResult<Book>();
			queryResult.setList(list);
			long total = array.get(0);
			queryResult.setTotalRecord(new Long(total).intValue());
		} catch (DataAccessException e) {
			throw new NotFountBookException("没有找到书籍");
		}
		return queryResult;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public QueryResult<Book> selectSameTypeBook(int id,QueryBean queryBean) throws NotFountBookException{
		QueryResult<Book> queryResult = null;
		List<Book> list = null;
		List<Long> array = null;
		final String hql = "select b from Book b where b.category.id=?";
		String hql2 = "select count(b) from Book b where b.category.id=?";
		final int startIndex=queryBean.getStartIndex();
		final int maxLines=queryBean.getPageSize();
		final int idd=id;
		try {
			list = hibernateTemplate.executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) 
					throws HibernateException, SQLException{
					Query query = session.createQuery(hql);
					query.setFirstResult(startIndex);
					query.setMaxResults(maxLines);
					query.setInteger(0, idd);
					return query.list();
				}
			});
			array = hibernateTemplate.find(hql2,id);
			queryResult = new QueryResult<Book>();
			queryResult.setList(list);
			long total = array.get(0);
			queryResult.setTotalRecord(new Long(total).intValue());
		} catch (DataAccessException e) {
			throw new NotFountBookException("没有找到该类书籍");
		}
		return queryResult;
	}


	@SuppressWarnings("unchecked")
	public List<Book> selectBookByNameAndAuthor(String some) throws NotFountBookException {
		List<Book> list=null;
		String hql="select b from Book b where b.name=? or b.author = ?";
		Object[] params = new Object[]{some,some};
		try {
			list=hibernateTemplate.find(hql, params);
			
		} catch (DataAccessException e) {
			throw new NotFountBookException("系统异常");
		}
		return list;
	}
}
