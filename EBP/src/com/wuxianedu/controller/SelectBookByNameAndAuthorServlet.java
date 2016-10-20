package com.wuxianedu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;

@WebServlet(name="SelectBookByNameAndAuthorServlet", urlPatterns={"/servlet/SelectBookByNameAndAuthorServlet"})
public class SelectBookByNameAndAuthorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");//设置传输编码
			
			String value=request.getParameter("value");
			value=new String(value.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("-----"+value);
			List<Book> list=null;
			
			SelectBookService ser=new SelectBookService();
			try {
				list=ser.selectBookByNameAndAuthor(value);
				request.setAttribute("list", list);
			} catch (NotFountBookException e) {
				request.setAttribute("exception", e.getMessage());
				request.getRequestDispatcher("/exception.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/booklist.jsp").forward(request, response);

			
			
	}

}
