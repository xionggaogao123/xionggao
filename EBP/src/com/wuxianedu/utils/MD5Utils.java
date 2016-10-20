package com.wuxianedu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class MD5Utils {

	public static String encode(String password) {
		String md5Pwd = null;
		// MD5
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] data = md.digest(password.getBytes()); // 128bit, 16byte
			// base64
			md5Pwd = Base64.encode(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5Pwd;
	}
	
	public static void main(String[] args) {
		String pwd = encode("123456abc");
		System.out.println(pwd);
	}
}
