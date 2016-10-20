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
import com.wuxianedu.service.AdminOrderService;

@Namespace(value="/admin")
@ParentPackage(value="struts-default")
@Action(value="GetOrderById")
@Results({  
   @Result(name="success", location="/WEB-INF/admin/orderInfo.jsp"), 

})
@Controller
@Scope("prototype")
public class GetOrderByIdAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String orderNum;
	@Resource
	private AdminOrderService adminOrderService;
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println(orderNum);
		List<Orders>list = adminOrderService.getOrderById(orderNum);
		System.out.println("list--"+list);
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
}
