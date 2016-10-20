package com.wuxianedu.dao;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.QueryResult;
import com.wuxianedu.utils.C3P0Utils;
import com.wuxianedu.web.QueryBean;

@Repository
public class AdminUserDAO {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
		@SuppressWarnings("unchecked")
		public User getUser(int id) throws AdminException{
			List<User>list = null;
			String hql = "select u from User u where u.id=?";
			try {
				list = hibernateTemplate.find(hql,id);
				if(list == null ||list.isEmpty()){
					throw new AdminException("没有找到对应的用户");
				}
				if(list.size()>1){
					throw new AdminException("重复的用户信息");
				}
				return list.get(0);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}

		}
		//分页
		public QueryResult<User> getAllUserList(QueryBean queryBean) throws AdminException{
			QueryResult<User>result = null;
			List<User>users = null;
			final String hql = "select u from User u";
			String hql2 = "select count(u) from User u";
			final int startIndex = queryBean.getStartUserIndex();
			final int usersize = queryBean.getPageUserSize();
			int totalRecode = 0;
			users = hibernateTemplate.executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					query.setFirstResult(startIndex);
					query.setMaxResults(usersize);
					return query.list();
				}
				
			});
			
			List<Long> list = hibernateTemplate.find(hql2);//统计book表中总的记录数
			long total = list.get(0);
			totalRecode = new Long(total).intValue();
			System.out.println(list.get(0));
			result = new QueryResult<User>();
			result.setTotalRecord(totalRecode);
			result.setList(users);
			return result;
		}
		
		//根据用户名查找
		@SuppressWarnings("unchecked")
		public User getUsreByName(String name) throws AdminException{
			List<User>list = null;
			String hql = "select u from User u where u.username=?";
			try {
				list = hibernateTemplate.find(hql, name);
				if(list == null ||list.isEmpty()){
					throw new AdminException("没有找到对应的用户");
				}
				if(list.size()>1){
					throw new AdminException("重复的用户信息");
				}
				return list.get(0);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}
		}
		
		//根据phone查找用户
		@SuppressWarnings("unchecked")
		public User getUserByPhone(String phone) throws AdminException{
			List<User>list = null;
			String hql = "select u from User u where u.phone=?";
			try {
				list = hibernateTemplate.find(hql, phone);
				if(list == null ||list.isEmpty()){
					throw new AdminException("没有找到对应的用户");
				}
				if(list.size()>1){
					throw new AdminException("重复的用户信息");
				}
				return list.get(0);
			} catch (DataAccessException e) {
				throw new AdminException(e.getMessage());
			}
		}
		//模糊查询
		public List<User> getUserByMoHu(String str) throws AdminException{
			
			QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
			List<User>userList = null;
			String sql  ="select * from user where username like ?";
			try {
				userList = (List<User>)runner.query(sql,new BeanListHandler(Book.class),new Object[]{"%"+str+"%"});
				if(userList ==null){
					throw new AdminException("没有找到对应的用户");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return userList;
		}
}
