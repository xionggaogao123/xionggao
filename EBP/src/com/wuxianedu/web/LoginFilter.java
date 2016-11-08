package com.wuxianedu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuxianedu.domain.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest )request;
		HttpServletResponse res=(HttpServletResponse) response;
		String uri=req.getRequestURI();
		
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null){ 
			chain.doFilter(request, response);
			
		}else{ 
			String contextPath = req.getServletContext().getContextPath();
			req.getSession().setAttribute("uri", uri);
			res.sendRedirect(contextPath + "/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
