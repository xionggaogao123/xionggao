package com.wuxianedu.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Orders {
	@Id
	private String id;
	private String orderNum;
	private Timestamp createTime;
	private double price;
	private boolean isDelete;
	
	@ManyToOne
	private User user;
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="order")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Item> items = new HashSet<Item>();
	
	public Orders(){}

	public Orders(String id, String orderNum, Timestamp createTime,
			double price, boolean isDelete, User user, Set<Item> items) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.createTime = createTime;
		this.price = price;
		this.isDelete = isDelete;
		this.user = user;
		//this.items = items;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
