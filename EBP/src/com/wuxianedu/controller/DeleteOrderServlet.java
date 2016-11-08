package com.wuxianedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wuxianedu.exception.OrdersException;
import com.wuxianedu.service.OrderService;

@WebServlet(name="DeleteOrderServlet", urlPatterns={"/servlet/DeleteOrderServlet"}) 
public class DeleteOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String orderId = request.getParameter("id");
		OrderService orderSvc = new OrderService();
		try {
			orderSvc.delOrder(orderId);
		} catch (OrdersException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/servlet/SelectOrdersServlet").forward(request, response);
		}
		
	}
}
