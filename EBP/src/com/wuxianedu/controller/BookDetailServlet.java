package com.wuxianedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;

@WebServlet(name="BookDetailServlet", urlPatterns={"/servlet/BookDetailServlet"}) 
public class BookDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String id=request.getParameter("id");
			SelectBookService service=new SelectBookService();
			Book book = null;
			try {
				book = service.selectABook(id);
			} catch (NotFountBookException e) {
				request.setAttribute("exception", e.getMessage());
				request.getRequestDispatcher("/exception.jsp").forward(request, response);
				return;
			}
			request.getSession().setAttribute("bookdetail", book);
			request.getRequestDispatcher("/bookdetail.jsp").forward(request, response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
