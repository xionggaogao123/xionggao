package com.wuxianedu.web;


public class QueryBean {
	
	private int currentPage = 1;//当前页码数
	private int pageSize = 9;//当前页显示记录的条数
	
	private int pageUserSize = 5;
	private int pageBookSize = 6;
	private int pageOrderSize = 4;
	private int startIndex;//当前页显示记录在数据表的其实位置
	private int startBookIndex;
	private int startUserIndex;
	private int startOrderIndex;
	
	public QueryBean() {
		super();
	}

	
	public int getPageUserSize() {
		return pageUserSize;
	}


	public void setPageUserSize(int pageUserSize) {
		this.pageUserSize = pageUserSize;
	}


	public int getPageBookSize() {
		return pageBookSize;
	}


	public void setPageBookSize(int pageBookSize) {
		this.pageBookSize = pageBookSize;
	}


	public int getPageOrderSize() {
		return pageOrderSize;
	}


	public void setPageOrderSize(int pageOrderSize) {
		this.pageOrderSize = pageOrderSize;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		startIndex = (currentPage - 1) * pageSize;
		return startIndex;
	}
	
	public int getStartBookIndex() {
		startBookIndex = (currentPage -1)*pageBookSize;
		return startBookIndex;
	}


	public void setStartBookIndex(int startBookIndex) {
		this.startBookIndex = startBookIndex;
	}


	public int getStartUserIndex() {
		startUserIndex = (currentPage -1)*pageUserSize;
		return startUserIndex;
	}

	public void setStartUserIndex(int startUserIndex) {
		this.startUserIndex = startUserIndex;
	}


	public int getStartOrderIndex() {
		startOrderIndex = (currentPage-1)*pageOrderSize;
		return startOrderIndex;
	}


	public void setStartOrderIndex(int startOrderIndex) {
		this.startOrderIndex = startOrderIndex;
	}


	@Override
	public String toString() {
		return "QueryBean [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", pageUserSize=" + pageUserSize
				+ ", pageBookSize=" + pageBookSize + ", pageOrderSize="
				+ pageOrderSize + ", startIndex=" + startIndex + "]";
	}
	
}
