package com.wuxianedu.web;

import java.util.HashMap;
import java.util.Map;

public class UserBean {
	
	private String name;
	private String username;
	private String password;
	private String repassword;
	private String phone;
	private String gender;
	private Map<String, String> errors = new HashMap<String, String>();
	public UserBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public boolean isRight(){
		
		if(password == null || password.trim().equals("")){
			errors.put("password", "密码不能为空");
		}else{
			if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")){
				errors.put("password", "密码是 6-20位字母和数字组合");
			}
		}
		
		if(repassword == null || repassword.trim().equals("")){
			errors.put("repassword", "两次密码不一致");
		}else{
			if(!repassword.equals(password)){
				errors.put("repassword", "两次密码不一致");
			}
		}
		
		if(phone == null || phone.trim().equals("")){
			errors.put("phone", "手机号码不能为空");
		}else{
			if(!phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$")){
				errors.put("phone", "手机号码不合法");
			}
		}
		if(!errors.isEmpty()){
			return false;
		}
		return true;
	}

}
