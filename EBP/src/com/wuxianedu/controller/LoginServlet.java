package com.wuxianedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuxianedu.domain.User;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;
import com.wuxianedu.web.LoginFormBean;
import com.wuxianedu.web.WebUtil;
@WebServlet(name="LoginServlet", urlPatterns={"/servlet/LoginServlet"})
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3816759504417171539L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path = request.getContextPath();
		LoginFormBean loginFormBean = WebUtil.regidter2FormBean(request, LoginFormBean.class);
		
		if(loginFormBean == null){
			response.sendRedirect(path + "/errors/error.jsp");
		}
		request.setAttribute("formBeans", loginFormBean);
		
		boolean isError = loginFormBean.viladate(request);
		
		if(!isError){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
			
		try {
			UserService userService = new UserService();
			User user = userService.login(loginFormBean.getUsername(), loginFormBean.getPassword());
			request.getSession().setAttribute("user", user);
			try {
				Thread.sleep(1*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(request.getSession().getAttribute("uri")!=null){
				String uuu=(String) request.getSession().getAttribute("uri");
				request.getSession().setAttribute("uri", null);
				response.sendRedirect(uuu);
				return;
			}
			
			response.sendRedirect("/EBP/index.jsp");
		} catch (UserListException e) {
			loginFormBean.getErrors().put("action", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}		
	}

}
