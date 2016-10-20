
package com.wuxianedu.action;


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
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;




@Namespace("/book")
@ParentPackage("struts-default")
@Action("SelectAllBook")
@Controller
@Scope("prototype")
@Results({
	@Result(name="input", location="/index.jsp"),
	@Result(name="error", location="/exception.jsp"),
	@Result(name="success", location="/shouye.jsp")
})
public class SelectAllBookAction extends ActionSupport {

	private static final long serialVersionUID = -644104030672916209L;

	@Resource
	private SelectBookService selectBookService;
	
	
	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		// 获取页面信息
		QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
		Object[] listBook = null;
		try {
			listBook = selectBookService.selectAllBook(queryBean);
		} catch (NotFountBookException e) {
			addActionError(getText("error.book.select", new String[]{e.getMessage()}));
			return ERROR;
		}
		// 如果没找到 将信息加入ActionError
		if(listBook == null){
			addActionError(getText("error.book.notFound"));
			return INPUT;
		}
		request.setAttribute("listBook", listBook);
		return SUCCESS;
	}

}

