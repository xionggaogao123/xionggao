package com.wuxianedu.domain;

public class ItemBook {
	
	private int id;
	private double total;
	private int amount;
	private String name;
	private double price;
	private String filename;//图片位置
	private String author;
	
	public ItemBook(){}

	public ItemBook(int id, double total, int amount, String name,
			double price, String filename, String author) {
		super();
		this.id = id;
		this.total = total;
		this.amount = amount;
		this.name = name;
		this.price = price;
		this.filename = filename;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		total = amount * price;
		this.total = Math.round(total * 100)/100.0;
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	
	
}
