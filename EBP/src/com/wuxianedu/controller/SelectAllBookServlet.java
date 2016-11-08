package com.wuxianedu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;

@WebServlet(name="SelectAllBookServlet", urlPatterns={"/servlet/SelectAllBookServlet"})
public class SelectAllBookServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 错误MAP集合 用于页面显示
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		// 获取页面信息
		QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
		AbstractApplicationContext context = (AbstractApplicationContext)getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		SelectBookService selectBookService= (SelectBookService)context.getBean("selectBookService");
		Object[] listBook = null;
		try {
			listBook = selectBookService.selectAllBook(queryBean);
		} catch (NotFountBookException e) {
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("/exception.jsp").forward(request, response);
			return;
		}
		// 如果没找到 将信息加入错误集合
		if(listBook == null){
			errors.put("NotFound", "没有找到");
			request.getRequestDispatcher("/shouye.jsp").forward(request, response);
			return;
		}
		request.setAttribute("listBook", listBook);
		request.getRequestDispatcher("/shouye.jsp").forward(request, response);
	}

}
