package com.wuxianedu.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.Category;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.QueryResult;
import com.wuxianedu.utils.C3P0Utils;
import com.wuxianedu.web.QueryBean;


@Repository
public class AdminBookDAO {
	
		
		@Resource
		private HibernateTemplate hibernateTemplate;
	
		//分页
				public QueryResult<Book> getAllBookList(QueryBean queryBean) throws AdminException{
					QueryResult<Book>result = null;
					List<Book>books = null;
					final String hql = "select b from Book b";
					String hql2 = "select count(b) from Book b";
					final int startIndex = queryBean.getStartBookIndex();
					final int booksize = queryBean.getPageBookSize();
					int totalRecode = 0;
					books = hibernateTemplate.executeFind(new HibernateCallback() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createQuery(hql);
							query.setFirstResult(startIndex);
							query.setMaxResults(booksize);
							return query.list();
						}
						
					});
					
					List<Long> list = hibernateTemplate.find(hql2);//统计book表中总的记录数
					long total = list.get(0);
					totalRecode = new Long(total).intValue();
					System.out.println(list.get(0));
					result = new QueryResult<Book>();
					result.setTotalRecord(totalRecode);
					result.setList(books);
					return result;
				}
			
		//搜索书名得到图书
		public Book getBook(String name) throws AdminException{
			List<Book>list = null;
			String hql = "select b from Book b where b.name=?";
			try {
				list = hibernateTemplate.find(hql, name);
				if(list == null ||list.isEmpty()){
					throw new AdminException("没有找到对应的图书");
				}
				if(list.size()>1){
					throw new AdminException("重复的图书信息");
				}
				return list.get(0);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}
			
		}	
	
		//根据id查找图书信息
		public Book getBookInfoById(int id) throws AdminException{
			List<Book>list = null;
			String hql = "select b from Book b where b.id=?";
			try {
				list = hibernateTemplate.find(hql,id);
				if(list == null ||list.isEmpty()){
					throw new AdminException("没有找到对应的图书");
				}
				if(list.size()>1){
					throw new AdminException("重复的图书信息");
				}
				return list.get(0);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}
		}
		
		//更新图书
		public void updateBook(Book book) throws AdminException{
			try {
				hibernateTemplate.update(book);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}
		}
		
		//添加图书
		public void addBook(Book book) throws AdminException{
			
			try {
				hibernateTemplate.save(book);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}
		}
		
			//查询parent_id字段为null的图书类别
			public List<Category> getCategory(){
				List<Category>list = null;
				String hql = "select c from Category c  where c.parent_id is null";
				list = hibernateTemplate.find(hql);
				return list;
			}
			
			public List<Category> getCategoriesByParentId(int id){
				List<Category>list = null;
				System.out.println(id);
				String hql = "select c from Category c where c.parent.id=?";
				list = hibernateTemplate.find(hql,id);
				return list;
			}
			
			//模糊查询
			public List<Book> getBookByMoHu(String str){
				List<Book>booklist = null;
				
				return booklist;
			}
}
