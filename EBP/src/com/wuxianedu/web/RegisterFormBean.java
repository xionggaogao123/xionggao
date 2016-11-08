package com.wuxianedu.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RegisterFormBean {
	
	private String username;
	private String password;
	private String password2;
	private String verifyCode;
	
	Map<String, String> errors = new HashMap<String, String>();
	
	public RegisterFormBean() {
		super();
	}

	public RegisterFormBean(String username, String password, String password2,
			Map<String, String> errors) {
		super();
		
		this.username = username;
		this.password = password;
		this.password2 = password2;
		this.errors = errors;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public boolean viladate(HttpServletRequest request){
		if(username == null || username.trim().equals("")){
			errors.put("username", "账号不能为空");
		}else{
			if(!username.matches("[\\u4e00-\\u9fa5a-zA-Z0-9\\-]{4,20}")){
				errors.put("username", "用户名为4-20位字符可由中文,英文,数字'-'组成");
			}
		}
		
		if(password == null || password.trim().equals("")){
			errors.put("password", "密码不能为空");
		}else{
			if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")){
				errors.put("password", "密码是 6-20位字母和数字组合");
			}
		}
		
		if(password2 == null || password2.trim().equals("")){
			errors.put("password2", "重复密码不能为空");
		}else{
			if(!password2.equals(password)){
				errors.put("password2", "两次密码不一致");
			}
		}
		
		String yzm =(String) request.getSession().getAttribute("yzm");
		
		
		if(verifyCode ==null ||verifyCode.trim().equals("")){
			errors.put("verifyCode", "验证码不能为空");
		}else{
			if(!verifyCode.equals(yzm)){
				errors.put("verifyCode", "验证码不正确");
			}
		}
		if(!errors.isEmpty()){
			return false;
		}
		return true;
	}
}