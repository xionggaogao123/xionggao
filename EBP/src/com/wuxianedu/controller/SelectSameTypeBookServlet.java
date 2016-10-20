package com.wuxianedu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.PageBean;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;

@WebServlet(name="SelectSameTypeBookServlet", urlPatterns={"/servlet/SelectSameTypeBookServlet"})
public class SelectSameTypeBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String currentPage=request.getParameter("currentPage");
		if((id==null)&&(currentPage==null)){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}else{
			
		if(id!=null){
			request.getSession().setAttribute("selectSameBookId", id);
		}
		// 错误MAP集合 用于页面显示
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		// 获取页面信息
		QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
		SelectBookService selectBookSvc = new SelectBookService();
		String bookId=(String) request.getSession().getAttribute("selectSameBookId");
		PageBean<Book> pageBean = null;
		try {
			pageBean = selectBookSvc.selectSameTypeBook(bookId,queryBean);
		} catch (NotFountBookException e) {
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("/exception.jsp").forward(request, response);
			return;
		}
		// 如果没找到 将信息加入错误集合
		if(pageBean == null){
			errors.put("NotFound", "没有找到");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/shuji.jsp").forward(request, response);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
		
	}

}
