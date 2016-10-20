<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.wuxianedu.domain.Category"%>
<%@page import="com.wuxianedu.service.AdminBookService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%  
	AbstractApplicationContext context =  (AbstractApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
  	AdminBookService adminBookService = (AdminBookService) context.getBean("adminBookService");
  	String strId = request.getParameter("id");
  	int id = Integer.parseInt(strId);
  	List<Category>list = adminBookService.getCategoriesById(id);
  	JSONArray jsonArray = JSONArray.fromObject(list);
	response.setContentType("text/javascript");
	response.getWriter().print(jsonArray);
	System.out.println(jsonArray);
%>
