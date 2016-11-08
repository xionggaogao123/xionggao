package com.wuxianedu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wuxianedu.domain.Orders;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.OrdersException;
import com.wuxianedu.service.OrderService;

@Namespace("/book")
@ParentPackage("struts-default")
@Action("SelectOrders")
@Controller
@Scope("prototype")
@Results({
	@Result(name="input", location="/index.jsp"),
	@Result(name="error", location="/exception.jsp"),
	@Result(name="success", location="/vip/ordersSelect.jsp")
})
public class SelectOrdersAction extends ActionSupport {

	private static final long serialVersionUID = -4806360390908331478L;
	
	@Resource
	private OrderService orderService;

	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		
		User user = (User)request.getSession().getAttribute("user");
		List<Orders> orderList = null;
		try {
			orderList = orderService.selectOrders(user);
		} catch (OrdersException e) {
			addActionError(getText("error.orders.select", new String[]{e.getMessage()}));
			return ERROR;
		} catch (NotFountBookException e) {
			addActionError(getText("error.orders.select", new String[]{e.getMessage()}));
			return ERROR;
		}
		request.setAttribute("orderList", orderList);
		return SUCCESS;
	}
	
	

}
