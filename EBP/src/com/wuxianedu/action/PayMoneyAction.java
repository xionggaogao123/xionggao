package com.wuxianedu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.CreateOrderException;
import com.wuxianedu.exception.NotEnoughBookException;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.PayMoneyDAOException;
import com.wuxianedu.service.PayMoneyService;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.BookNumberBean;


@Namespace("/book")  
@ParentPackage(value="struts-default")
@Action(value="PayMoney")
@Results({
	@Result(name="success", location="/vip/paysuccess.jsp"),
	@Result(name="input", location="/${returnUrl}"),
	@Result(name="error", location="/exception.jsp"),
	@Result(name="prefer", type="redirect",location="/index.jsp")
})
@Controller
@Scope("prototype")
public class PayMoneyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private PayMoneyService payMoneyService;
	@Resource
	private SelectBookService selectBookService;
	private String returnUrl; 
	private String id;
	private String mark;
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public PayMoneyAction() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}


	@SuppressWarnings("unchecked")
	@Override
	public String execute(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		double totaltotal=0;
		
			Map<String,Exception> errorMap=new HashMap<String,Exception>();
			Map<String, String> errorMap2 = new HashMap<String,String>();
			request.setAttribute("errormap2", errorMap2);
			List<BookNumberBean> list=new ArrayList<BookNumberBean>();
			User user=(User) request.getSession().getAttribute("user");
			//多本支付
			if("true".equals(mark)){
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				totaltotal=0;
				for (BookNumberBean bean :list) {
					totaltotal=totaltotal+(bean.getNumber()*bean.getBook().getNewPrice());
				}
				if(totaltotal>user.getBalance()){
					request.setAttribute("notEnoughMoney", "余额不足，请充值，谢谢！");
					returnUrl="book/UpdateBalance.action";
					return "input";
				}
				for (BookNumberBean bean :list) {
					try {
						if(bean.getNumber()>selectBookService.selectABook(bean.getBook().getId()+"").getAmount()){
							errorMap.put("notBook"+bean.getBook().getId(),new NotEnoughBookException(selectBookService.selectABook(bean.getBook().getId()+"").getName()+"的书本数量不足"));
							request.setAttribute("notEnoughBook",bean.getBook().getName()+"书籍库存数量不足，请选择其它书籍，请见谅！");
							returnUrl="shoppingcart.jsp";
							return "input";
						}
					} catch (NotFountBookException e) {
						request.setAttribute("exception", e.getMessage());
						return "error";
						
					}
				}
				if(errorMap.isEmpty() && errorMap2.isEmpty()){
					//扣钱、减书
					User user1=(User) request.getSession().getAttribute("user");
					try {
						payMoneyService.payAll(list, user1);
					} catch (PayMoneyDAOException e) {
						request.setAttribute("exception", e.getMessage());
						return "error";
					} catch (CreateOrderException e) {
						request.setAttribute("exception", e.getMessage());
						return "error";
					}
					List<BookNumberBean> list1=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
					list1.clear();
					request.getSession().setAttribute("shoppingcart", list1);
					return "success";
				}
			}
			//一本支付
			else if(id!=null){
				Book book = null;
				try {
					book = selectBookService.selectABook(id);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					return "error";
				}
				if(book.getNewPrice()>user.getBalance()){
					request.setAttribute("notEnoughMoney", "余额不足，请充值，谢谢！");
					returnUrl=null;
					returnUrl="book/UpdateBalance.action";
					return "input";
				}
	
				try {
					if(1>selectBookService.selectABook(id).getAmount()){
						errorMap.put("notBook",new NotEnoughBookException(selectBookService.selectABook(id).getName()+"的书本数量不足"));
						Book book1 = null;
						try {
							book1 = selectBookService.selectABook(id);
						} catch (NotFountBookException e) {
							request.setAttribute("exception", e.getMessage());
							return "error";
						}
						request.getSession().setAttribute("bookdetail", book1);
						request.setAttribute("notOneEnoughBook",selectBookService.selectABook(id).getName()+"书籍库存数量不足，请选择其它书籍，请见谅！");
						returnUrl=null;
						returnUrl="bookdetail.jsp";
						return "input";
						}
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					return "error";
				}
				if(errorMap.isEmpty()){
					//扣钱、减书
					Book book1 = null;
					try {
						book1 = selectBookService.selectABook(id);
					} catch (NotFountBookException e) {
						request.setAttribute("exception", e.getMessage());
						return "error";
					}
					User user1=(User) request.getSession().getAttribute("user");
					try {
						payMoneyService.payOne(user1, book1);
					} catch (PayMoneyDAOException e) {
						request.setAttribute("exception", e.getMessage());
						return "error";
					} catch (CreateOrderException e) {
						request.setAttribute("exception", e.getMessage());
						return "error";
					}
					
					return "success";
				}
			}else{
		returnUrl="index.jsp";
		return "input";
		}
			returnUrl="index.jsp";
			return "input";
	}
	
	
	
	

}
