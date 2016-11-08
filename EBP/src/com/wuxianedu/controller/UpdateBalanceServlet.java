package com.wuxianedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuxianedu.domain.User;
import com.wuxianedu.exception.BalanceException;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;

@WebServlet(name="UpdateBalanceServlet", urlPatterns={"/servlet/UpdateBalanceServlet"})
public class UpdateBalanceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		UserService userSvc = new UserService();
		User newUser = null;
		try {
			newUser = userSvc.selectBalance(user);
		} catch (UserListException e) {
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("/exception.jsp").forward(request, response);
			return;
		} catch (BalanceException e) {
			request.setAttribute("exception", e.getMessage());
			request.getRequestDispatcher("/exception.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", newUser);
		request.getRequestDispatcher("/vip/myBalance.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String hidden=request.getParameter("hidden");
		//防止重复注册
		HttpSession session=request.getSession();
		String formId=(String) session.getAttribute("token");
		if(formId == null){
			formId = "";
		}
		if(hidden.equals(formId)){
			session.removeAttribute("token");
			
			String username = request.getParameter("username");
			String balance =request.getParameter("balance");
			String rex = "^\\d+(\\.\\d+)?$";
			Pattern p = Pattern.compile(rex);
			Matcher m = p.matcher(balance);
			if (!m.find()){
				request.setAttribute("inputerror", "请输入正确金额");
				request.getRequestDispatcher("/vip/myBalance.jsp").forward(request, response);
				return;
			}
			double newBalance = Double.valueOf(balance);
			UserService userSvc = new UserService();
			try {
				User user = (User)session.getAttribute("user");
				User updateUser = new User();
				updateUser.setId(user.getId());
				updateUser.setName(user.getName());
				updateUser.setUsername(user.getUsername());
				updateUser.setPassword(user.getPassword());
				updateUser.setRegisterTime(user.getRegisterTime());
				updateUser.setPhone(user.getPhone());
				updateUser.setGender(user.getGender());
				updateUser.setBalance(newBalance);
				userSvc.updateBalance(updateUser);
				session.setAttribute("user", updateUser);
			} catch (BalanceException e) {
				request.setAttribute("exception", e.getMessage());
				request.getRequestDispatcher("/exception.jsp").forward(request, response);
				return;
			}
			String path = request.getContextPath();
			response.sendRedirect(path + "/balanceSuccess.jsp");
		}
	}

}
