package com.wuxianedu.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;

	@ManyToOne
	private Category parent;
	
	public Category(){}

	public Category(int id, String name, Category parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	

	
}
