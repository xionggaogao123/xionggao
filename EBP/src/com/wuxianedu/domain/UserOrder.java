package com.wuxianedu.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class UserOrder {
	
	private String id;
	private String username;
	private Timestamp createTime;
	private String orderNum;
	private double price;
	private Set<ItemBook> set = new HashSet<ItemBook>();
	public UserOrder(){}

	public UserOrder(String id, String username, Timestamp createTime, String orderNum,
			double price) {
		super();
		this.id = id;
		this.username = username;
		this.createTime = createTime;
		this.orderNum = orderNum;
		this.price = price;
	}
	
	public Set<ItemBook> getSet() {
		return set;
	}

	public void setSet(Set<ItemBook> set) {
		this.set = set;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public double getPrice() {
		price = 0;
		for(ItemBook itemBook:set){
			price = itemBook.getTotal();
		}
		this.price = Math.round(price * 100)/100.00;
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
