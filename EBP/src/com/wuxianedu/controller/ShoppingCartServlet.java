package com.wuxianedu.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wuxianedu.domain.Book;
import com.wuxianedu.exception.NotFountBookException;
import com.wuxianedu.service.SelectBookService;
import com.wuxianedu.web.BookNumberBean;

@WebServlet(name="ShoppingCartServlet", urlPatterns={"/servlet/ShoppingCartServlets"})
public class ShoppingCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    double totaltotal=0;
			DecimalFormat  df= new DecimalFormat("######0.00");   
			List<BookNumberBean> list=new ArrayList<BookNumberBean>();
			Map<String,String> map=new HashMap<String,String>();
			BookNumberBean bookBean=new BookNumberBean();
			String add=request.getParameter("add");
			String del=request.getParameter("del");
			String addnum=request.getParameter("addnum");
			String delnum=request.getParameter("delnum");
			String input=request.getParameter("input");
			String error=request.getParameter("error");
			if(error!=null){
				SelectBookService service=new SelectBookService();
				Book bookerr = null;
				try {
					bookerr = service.selectABook(error);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				for (BookNumberBean bean :list) {
					if(bean.getBook().getId()==bookerr.getId()){
						int number=bean.getNumber();
						map.put("yuan", number+"");
					}
				}
			}
			if(input!=null){
				String data[]=input.split(";");
				String rex = "^\\+?[0-9][0-9]*$";
				Pattern p = Pattern.compile(rex);
				Matcher m = p.matcher(data[1]);
				if (!m.find()){
				   return;
				}
				SelectBookService service=new SelectBookService();
				Book book = null;
				try {
					book = service.selectABook(data[0]);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				double inputPrice = book.getNewPrice() * Double.parseDouble(data[1]);
				map.put("totalPrice", df.format(inputPrice) + "");
					for (BookNumberBean bean :list) {
						if(bean.getBook().getId()==book.getId()){
							bookBean=bean;
							bookBean.setNumber(Integer.parseInt(data[1]));
							HttpSession session=request.getSession();
							session.setAttribute("shoppingcart", list);
							for (BookNumberBean bean1 :list) {
								totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
							}
							map.put("total", df.format(totaltotal)+"");
							session.setAttribute("total", df.format(totaltotal)+"");
							totaltotal=0;
						}
					}
					
			}
			if(addnum!=null){
				SelectBookService service=new SelectBookService();
				Book book = null;
				try {
					book = service.selectABook(addnum);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
					for (BookNumberBean bean :list) {
						if(bean.getBook().getId()==book.getId()){
							bookBean=bean;
							bookBean.setNumber(bean.getNumber()+1);
							double inputPrice = book.getNewPrice() * bookBean.getNumber();
							map.put("totalPrice", df.format(inputPrice) + "");
							HttpSession session=request.getSession();
							session.setAttribute("shoppingcart", list);
							for (BookNumberBean bean1 :list) {
								totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
							}
							map.put("total", df.format(totaltotal)+"");
							session.setAttribute("total", df.format(totaltotal)+"");
							totaltotal=0;
						}
					}
					
				
			}else if(delnum!=null){
				SelectBookService service=new SelectBookService();
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				Book book = null;
				try {
					book = service.selectABook(delnum);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				for (BookNumberBean bean :list) {
					if(bean.getBook().getId()==book.getId()){
						bookBean=bean;
						if(bookBean.getNumber()==0){
							HttpSession session=request.getSession();
							session.setAttribute("shoppingcart", list);
							for (BookNumberBean bean1 :list) {
								totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
							}
							map.put("total", df.format(totaltotal)+"");
							session.setAttribute("total", df.format(totaltotal)+"");
							totaltotal=0;
						}else{
							bookBean.setNumber(bean.getNumber()-1);
							double inputPrice = book.getNewPrice() * bookBean.getNumber();
							map.put("totalPrice", df.format(inputPrice) + "");
							HttpSession session=request.getSession();
							session.setAttribute("shoppingcart", list);
							for (BookNumberBean bean1 :list) {
								totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
							}
							map.put("total", df.format(totaltotal)+"");
							session.setAttribute("total", df.format(totaltotal)+"");
							totaltotal=0;
						}
						
					}
				}
			}
			
			
			
			
			if(add!=null){
				Boolean is=true;
				SelectBookService service=new SelectBookService();
				Book book = null;
				try {
					book = service.selectABook(add);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				if(list!=null){
					for (BookNumberBean bean :list) {
						if(bean.getBook().getId()==book.getId()){
							is=false;
							bookBean=bean;
							bookBean.setNumber(bean.getNumber()+1);
							double inputPrice = book.getNewPrice() * bookBean.getNumber();
							map.put("totalPrice", df.format(inputPrice) + "");
							HttpSession session=request.getSession();
							session.setAttribute("shoppingcart", list);
							for (BookNumberBean bean1 :list) {
								totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
							}
							map.put("total", df.format(totaltotal)+"");
							session.setAttribute("total", df.format(totaltotal)+"");
							totaltotal=0;
							return;
						}
					}}
				else{
					list=new ArrayList<BookNumberBean>();
				}
					if(is){
						bookBean.setBook(book);
						bookBean.setNumber(1);
						double inputPrice = book.getNewPrice() * bookBean.getNumber();
						map.put("totalPrice", df.format(inputPrice) + "");
						list.add(bookBean);
						HttpSession session=request.getSession();
						session.setAttribute("shoppingcart", list);
						for (BookNumberBean bean1 :list) {
							totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
						}
						map.put("total", df.format(totaltotal)+"");
						session.setAttribute("total", df.format(totaltotal)+"");
						totaltotal=0;
					}
					
			}
			else if(del!=null){
				list=(List<BookNumberBean>) request.getSession().getAttribute("shoppingcart");
				SelectBookService service=new SelectBookService();
				Book book = null;
				try {
					book = service.selectABook(del);
				} catch (NotFountBookException e) {
					request.setAttribute("exception", e.getMessage());
					request.getRequestDispatcher("/exception.jsp").forward(request, response);
					return;
				}
				map.put("newPrice", df.format(book.getNewPrice())+"");
				for (BookNumberBean bean1 :list) {
					if(bean1.getBook().getId()==book.getId()){
						bookBean=bean1;
					}
				}
				list.remove(bookBean);
				HttpSession session=request.getSession();
				session.setAttribute("shoppingcart", list);
				for (BookNumberBean bean1 :list) {
					totaltotal=totaltotal+(bean1.getNumber()*bean1.getBook().getNewPrice());
				}
				if(list.isEmpty()){
					map.put("isEmpty", "yes");
				}else{
					map.put("isEmpty", "no");
				}
				map.put("total", df.format(totaltotal)+"");
				session.setAttribute("total", df.format(totaltotal)+"");
				totaltotal=0;
				
			}
			Gson gson = new Gson();
			String json=gson.toJson(map).toString();
			response.setContentType("text/javascript");
			response.getWriter().print(json);	
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
