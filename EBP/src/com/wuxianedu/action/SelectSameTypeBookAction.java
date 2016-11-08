package com.wuxianedu.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;





import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.PageBean;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.QueryBean;
import com.wuxianedu.web.WebUtil;


@Namespace("/book")  
@ParentPackage(value="struts-default")
@Action(value="SelectSameTypeBook")
@Results({
	@Result(name="input", location="/index.jsp"),
	@Result(name="success", location="/shuji.jsp"),
	@Result(name="error", location="/exception.jsp")
})
@Controller
@Scope("prototype")
public class SelectSameTypeBookAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String currentPage;
	@Resource
	private SelectBookService selectBookService;
	public SelectSameTypeBookAction() {
		super();
	}
	public SelectSameTypeBookAction(String id, String currentPage,
			SelectBookService selectBookService) {
		super();
		this.id = id;
		this.currentPage = currentPage;
		this.selectBookService = selectBookService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		
		if((id==null)&&(currentPage==null)){
			return "input";
		}else{
			if(id!=null){
				request.getSession().setAttribute("selectSameBookId", id);
			}else{
				id=(String) request.getSession().getAttribute("selectSameBookId");
			}
		// 错误MAP集合 用于页面显示
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
		// 获取页面信息
			QueryBean queryBean = WebUtil.regidter2FormBean(request, QueryBean.class);
			if(currentPage!=null){
			queryBean.setCurrentPage(Integer.parseInt(currentPage));
			queryBean.setStartIndex((Integer.parseInt(currentPage) - 1) * queryBean.getPageSize());
			}
			PageBean<Book> pageBean = null;
			try {
				pageBean = selectBookService.selectSameTypeBook(id,queryBean);
			} catch (NotFountBookException e) {
				e.printStackTrace();
				request.setAttribute("exception", e.getMessage());
				return "error";
			}
		// 如果没找到 将信息加入错误集合
			if(pageBean == null){
				errors.put("NotFound", "没有找到");
				return "input";
			}
			request.setAttribute("pageBean", pageBean);
			return "success";
			
		}
	}

	
	

}
