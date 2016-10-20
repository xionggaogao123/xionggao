package com.wuxianedu.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.ItemBook;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.domain.UserOrder;
import com.wuxianedu.exception.AdminException;
import com.wuxianedu.service.QueryResult;
import com.wuxianedu.utils.C3P0Utils;
import com.wuxianedu.web.QueryBean;

@Repository
public class AdminOrder {

	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/*public List<Orders> getAllOrders(){
		List<Orders>list = null;
		String hql = "from Orders o";
		list = hibernateTemplate.find(hql);
		return list;
	}*/
	
	public QueryResult<Orders> getAllOrdersList(QueryBean queryBean) throws AdminException{
		QueryResult<Orders>result = null;
		List<Orders>orders = null;
		final String hql = "select o from Orders o";
		String hql2 = "select count(o) from Orders o";
		final int startIndex = queryBean.getStartOrderIndex();
		final int ordersize = queryBean.getPageOrderSize();
		int totalRecode = 0;
		orders = hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(startIndex);
				query.setMaxResults(ordersize);
				return query.list();
			}
			
		});
		List<Long> list = hibernateTemplate.find(hql2);//统计book表中总的记录数
		long total = list.get(0);
		totalRecode = new Long(total).intValue();
		System.out.println(list.get(0));
		result = new QueryResult<Orders>();
		result.setTotalRecord(totalRecode);
		result.setList(orders);
		return result;
	}
	
	//根据订单号查询
	public List<Orders> getOrderById(String orderId){
		List<Orders> list = null;
		String hql = "select o from Orders o where o.orderNum=?";
		list = hibernateTemplate.find(hql,orderId);
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}
