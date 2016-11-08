package com.wuxianedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuxianedu.domain.User;
import com.wuxianedu.exception.UserListException;
import com.wuxianedu.service.UserService;
import com.wuxianedu.web.UserBean;
import com.wuxianedu.web.WebUtil;

@WebServlet(name="UpdateUserServlet", urlPatterns={"/servlet/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hidden=request.getParameter("hidden");
		//防止重复注册
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("token");
		if(id == null){
			id = "";
		}
		if(hidden.equals(id)){
			session.removeAttribute("token");
		
			request.setCharacterEncoding("utf-8");
			String path = request.getContextPath();
			// 获取页面输入
			UserBean userBean = WebUtil.regidter2FormBean(request, UserBean.class);
			if(userBean == null){
				response.sendRedirect(path + "/vip/personalCenter.jsp");
				return;
			}
			request.setAttribute("userBean", userBean);
			// 对获取的数据进行校验
			boolean isRight = userBean.isRight();
			if(!isRight){
				request.getRequestDispatcher("/vip/personalCenter.jsp").forward(request, response);
				return;
			}
			// 调用service
			UserService userSvc = new UserService();
			try {
				User user = (User)request.getSession().getAttribute("user");
				User updateUser = new User();
				updateUser.setId(user.getId());
				updateUser.setName(userBean.getName());
				updateUser.setUsername(user.getUsername());
				updateUser.setPassword(userBean.getPassword());
				updateUser.setRegisterTime(user.getRegisterTime());
				updateUser.setPhone(userBean.getPhone());
				updateUser.setGender(userBean.getGender());
				updateUser.setBalance(user.getBalance());
				userSvc.updateUser(updateUser);
				request.getSession().setAttribute("user", updateUser);
			} catch (UserListException e) {
				request.setAttribute("exception", e.getMessage());
				request.getRequestDispatcher("/exception.jsp").forward(request, response);
				return;
			}
			response.sendRedirect(path + "/updateSuccess.jsp");
		}
	}

}
