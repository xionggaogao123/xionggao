package com.wuxianedu.service;

import java.util.List;

public class QueryResult<E> {
	
	private List<E> list;
	private int totalRecord;
	
	public QueryResult() {
		super();
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "QueryResult [list=" + list + ", totalRecord=" + totalRecord
				+ "]";
	}
	
}
