package com.wuxianedu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String descration;
	private double oldPrice;
	private double newPrice;
	private String filename;
	private String author;
	private int amount;
	
	@ManyToOne
	private Category category;
	
	public Book(){}
	
	public Book(int id,String name, String descration, double oldPrice,
			double newPrice, String author, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.descration = descration;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.author = author;
		this.amount = amount;
	}
	public Book(int id,String name,String filename, String descration, double oldPrice,
			double newPrice, String author, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.filename = filename;
		this.descration = descration;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.author = author;
		this.amount = amount;
	}
	
	
	public Book(String name, String descration, double oldPrice,
			double newPrice, String filename, String author, int amount) {
		super();
		this.name = name;
		this.descration = descration;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.filename = filename;
		this.author = author;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


}
