package com.wuxianedu.web;

import java.io.Serializable;

import com.wuxianedu.domain.Book;

public class BookNumberBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int number;
	private Book book;
	public BookNumberBean() {
		super();
	}
	public BookNumberBean(int number, Book book) {
		super();
		this.number = number;
		this.book = book;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	

}
