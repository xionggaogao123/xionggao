package com.wuxianedu.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtil {
	
	public static <T> T regidter2FormBean(HttpServletRequest request, Class<T> formClass){
		T bean = null;
		try {
			bean = formClass.newInstance();
			Enumeration<String> en = request.getParameterNames();
			while(en.hasMoreElements()){
				String name = en.nextElement();
				String value = request.getParameter(name);
				BeanUtils.copyProperty(bean, name, value);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
