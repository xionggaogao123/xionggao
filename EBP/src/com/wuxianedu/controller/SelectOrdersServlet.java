package com.wuxianedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.Item;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.OrdersException;
import com.wuxianedu.service.OrderService;

@WebServlet(name="SelectOrdersServlet", urlPatterns={"/servlet/SelectOrdersServlet"})
public class SelectOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");
		OrderService orderSvc = new OrderService();
		List<Orders> orderList = null;
		try {
			orderList = orderSvc.selectOrders(user);
		} catch (OrdersException e) {
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("/exception.jsp").forward(request, response);
			return;
		} catch (NotFountBookException e) {
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("/exception.jsp").forward(request, response);
			return;
		}
//		Map<String, List<Item>> items = (Map<String, List<Item>>) orderList[1];
//		Map<Integer, Book> book = (Map<Integer, Book>) orderList[2];
		request.setAttribute("orderList", orderList);
//		request.setAttribute("itemList", items);
//		request.setAttribute("bookList", book);		
		request.getRequestDispatcher("/vip/ordersSelect.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
