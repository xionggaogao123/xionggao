package com.wuxianedu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuxianedu.domain.Book;
import com.wuxianedu.domain.User;
import com.wuxianedu.exception.CreateOrderException;
import com.wuxianedu.exception.NotEnoughBookException;
import com.wuxianedu.exception.NotEnoughMoneyException;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.exception.PayMoneyDAOException;
import com.wuxianedu.service.PayMoneyService;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.BookNumberBean;
import com.wuxianedu.web.Data;

@WebServlet(name="PayMoneyServlet", urlPatterns={"/servlet/PayMoneyServlet"})
public class PayMoneyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double totaltotal=0;
		String hidden=request.getParameter("hidden");
		if(hidden == null){
			response.sendRedirect("/EBP/index.jsp");
			return;
		}
		HttpSession session=request.getSession();
		String formId=(String) session.getAttribute("token");
		if(formId == null){
			formId = "";
		}
		if(hidden.equals(formId)){
			System.out.println("hidden进来了么？");
			session.removeAttribute("token");
		
			Map<String,Exception> errorMap=new HashMap<String,Exception>();
			Map<String, String> errorMap2 = new HashMap<String,String>();
			request.setAttribute("errormap2", errorMap2);
			List<BookNumberBean> list=new ArrayList<BookNumberBean>();
			String id=request.getParameter("id");
			String mark=request.getParameter("mark");
			User user=(User) request.getSession().getAttribute("user");
			SelectBookService service=new SelectBookService();
			
			//多本支付
			if("true".equals(mark)){
				System.out.println("是多本支付么?");
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				totaltotal=0;
				for (BookNumberBean bean :list) {
					totaltotal=totaltotal+(bean.getNumber()*bean.getBook().getNewPrice());
				}
				if(totaltotal>user.getBalance()){
					request.setAttribute("notEnoughMoney", "余额不足，请充值，谢谢！");
					request.getRequestDispatcher("/servlet/UpdateBalanceServlet").forward(request, response);
					return;
				}
				for (BookNumberBean bean :list) {
					try {
						System.out.println("wowowwowowoow = ");
						if(bean.getNumber()>service.selectABook(bean.getBook().getId()+"").getAmount()){
							System.out.println("111111111111111111111111111111111111111111");
							errorMap.put("notBook"+bean.getBook().getId(),new NotEnoughBookException(service.selectABook(bean.getBook().getId()+"").getName()+"的书本数量不足"));
							request.setAttribute("notEnoughBook",bean.getBook().getName()+"书籍库存数量不足，请选择其它书籍，请见谅！");
							request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
							return;
						}
					} catch (NotFountBookException e) {
						e.printStackTrace();
					}
				}
				if(errorMap.isEmpty() && errorMap2.isEmpty()){
					//扣钱、减书
					User user1=(User) request.getSession().getAttribute("user");
					PayMoneyService ser=new PayMoneyService();
					try {
						ser.payAll(list, user1);
					} catch (PayMoneyDAOException e) {
						request.setAttribute("exception", e.getMessage());
						request.getRequestDispatcher("/exception.jsp").forward(request, response);
						return;
					} catch (CreateOrderException e) {
						request.setAttribute("exception", e.getMessage());
						request.getRequestDispatcher("/exception.jsp").forward(request, response);
						return;
					}
					List<BookNumberBean> list1=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
					list1.clear();
					request.getSession().setAttribute("shoppingcart", list1);
					request.getRequestDispatcher("/vip/paysuccess.jsp").forward(request, response);
					
				}
			}
			//一本支付
			else{
				Book book = null;
				try {
					book = service.selectABook(id);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				if(book.getNewPrice()>user.getBalance()){
					request.setAttribute("notEnoughMoney", "余额不足，请充值，谢谢！");
					request.getRequestDispatcher("/servlet/UpdateBalanceServlet").forward(request, response);
					return;
				}
	
				try {
					if(1>service.selectABook(id).getAmount()){
						errorMap.put("notBook",new NotEnoughBookException(service.selectABook(id).getName()+"的书本数量不足"));
						request.setAttribute("notOneEnoughBook",service.selectABook(id).getName()+"书籍库存数量不足，请选择其它书籍，请见谅！");
						request.getRequestDispatcher("/bookdetail.jsp").forward(request, response);
						return;
						}
				} catch (NotFountBookException e1) {
					e1.printStackTrace();
				}
				if(errorMap.isEmpty()){
					//扣钱、减书
					Book book1 = null;
					try {
						book1 = service.selectABook(id);
					} catch (NotFountBookException e) {
						request.setAttribute("exception", e.getMessage());
						request.getRequestDispatcher("/exception.jsp").forward(request, response);
						return;
					}
					User user1=(User) request.getSession().getAttribute("user");
					PayMoneyService ser=new PayMoneyService();
					try {
						ser.payOne(user1, book1);
					} catch (PayMoneyDAOException e) {
						request.setAttribute("exception", e.getMessage());
						request.getRequestDispatcher("/exception.jsp").forward(request, response);
						return;
					} catch (CreateOrderException e) {
						request.setAttribute("exception", e.getMessage());
						request.getRequestDispatcher("/exception.jsp").forward(request, response);
						return;
					}
					List<BookNumberBean> list1=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
					list1.clear();
					request.getSession().setAttribute("shoppingcart", list1);
					request.getRequestDispatcher("/vip/paysuccess.jsp").forward(request, response);
				}
			}
		}else{
			String uri=request.getHeader("Referer");
			response.sendRedirect(uri);
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
