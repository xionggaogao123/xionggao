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
import javax.servlet.http.HttpSession;

import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;
import com.wuxianedu.web.RegisterFormBean;
import com.wuxianedu.web.WebUtil;
@WebServlet(name="RegisterServlet", urlPatterns={"/servlet/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		RegisterFormBean registerFormBean = WebUtil.regidter2FormBean(request, RegisterFormBean.class);
		
		if(registerFormBean == null){
			String path = request.getContextPath();
			response.sendRedirect(path + "/errors/error.jsp");
			return;	
		}
		boolean isError = registerFormBean.viladate(request);
		request.getSession().setAttribute("formBean", registerFormBean);
		
		
		if(!isError){
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
			
		
		
		try {
			UserService userService = new UserService();	
			String username = registerFormBean.getUsername();
			String password = registerFormBean.getPassword();
			userService.register(username, password);
			response.sendRedirect("/EBP/turn.jsp");
		} catch (UserListException e) {
			registerFormBean.getErrors().put("action", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}	
	}

}
