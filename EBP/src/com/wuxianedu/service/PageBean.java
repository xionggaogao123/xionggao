package com.wuxianedu.service;

import java.util.Arrays;
import java.util.List;


public class PageBean<E> {
	
	private List<E> list;
	private int totalRecord;
	private int pageSize;
	private int totalPage;
	private int currentPage;
	private int previousPage;
	private int nextPage;
	private int[] pageBar;
	
	public PageBean() {
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		if(totalRecord % pageSize == 0){
			totalPage = totalRecord/pageSize;
		}else{
			totalPage = totalRecord/pageSize + 1;
		}
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPreviousPage() {
		if(currentPage - 1 < 1){
			previousPage = 1;
		}else{
			previousPage = currentPage - 1;
		}
		return previousPage;
	}

	public int getNextPage() {
		if(currentPage + 1 > totalPage){
			nextPage = totalPage;
		}else{
			nextPage = currentPage + 1;
		}
		return nextPage;
	}

	public int[] getPageBar() {
		if(totalPage <= 10){
			pageBar = new int[totalPage];
			for(int i = 0; i < pageBar.length; i++){
				pageBar[i] = i + 1;
			}
		}else{
			pageBar = new int[10];
			int start = currentPage - 4;
			int end = currentPage + 5;
			
			if(start < 1){
				start = 1;
				end = 10;
			}
			
			if(end > totalPage){
				end = totalPage;
				start = totalPage - 9;
			}
			
			int index = 0;
			for(int i = start; i <= end; i++){
				pageBar[index++] = i;
			}
		}
		return pageBar;
	}

	@Override
	public String toString() {
		return "PageBean [list=" + list + ", totalRecord=" + totalRecord
				+ ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", currentPage=" + currentPage + ", previousPage="
				+ previousPage + ", nextPage=" + nextPage + ", pageBar="
				+ Arrays.toString(pageBar) + "]";
	}
}
