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

public class CharsetFilter implements Filter {
	private String encode = null;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//转换
		  HttpServletRequest req = (HttpServletRequest)request;
		  HttpServletResponse res = (HttpServletResponse)response;
		  
		  /*
		   * 判断在web.xml文件中是否配置了编码格式的信息
		   * 如果为空，则设置编码格式为配置文件中的编码格式
		   * 否则编码格式设置为UTF-8
		   */
		  if(this.encode != null && !this.encode.equals("")){
			  req.setCharacterEncoding(this.encode);
			  res.setCharacterEncoding(this.encode);
		  }else{
		   request.setCharacterEncoding("UTF-8");
		   response.setCharacterEncoding("UTF-8");
		  }
		  
		  /*
		   * 使用doFilter方法调用链中的下一个过滤器或目标资源（servlet或JSP页面）。
		   * chain.doFilter处理过滤器的其余部分（如果有的话），最终处理请求的servlet或JSP页面。
		   */
		  chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
