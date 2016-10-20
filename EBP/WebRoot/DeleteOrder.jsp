<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext"%>
<%@page import="com.wuxianedu.exception.OrdersException"%>
<%@page import="com.wuxianedu.service.OrderService"%>
<%

	AbstractApplicationContext context = (AbstractApplicationContext)application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	OrderService orderSvc = (OrderService)context.getBean("orderService");
	String orderId = request.getParameter("id");
	try {
		orderSvc.delOrder(orderId);
	} catch (OrdersException e) {
		request.setAttribute("error", e.getMessage());
		request.getRequestDispatcher("/servlet/SelectOrdersServlet").forward(request, response);
	}
%>