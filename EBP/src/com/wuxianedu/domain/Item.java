package com.wuxianedu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int id;
	private double price;
	private int amount;
	
	@ManyToOne
	private Orders order;
	
	@OneToOne
	private Book book;
	
	public Item(){}
	
	public Item(int id, double price, int amount, Orders order, Book book) {
		super();
		this.id = id;
		this.price = price;
		this.amount = amount;
		this.order = order;
		this.book = book;
	}

	public Item(int id, double price, int amount, String order_id, int book_id) {
		super();
		this.id = id;
		this.price = price;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Orders getOrder() {
		return order;
	}
	
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
}
