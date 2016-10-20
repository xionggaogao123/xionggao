<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addemployee.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  		<h4>添加新员工</h4>
  		<form action="${pageContext.request.contextPath}/savaEmployee.action" method="post">
  			lastName:<input type="text" name="lastName"/><br>
  			email:<input type="text" name="email"/><br>
			department:
			<select name="select" >
				<c:forEach items="${list}" var="department">
					<option value="${department.departmentName }">${department.departmentName}</option>
				</c:forEach>	
			</select><br>
			<input type="submit" value="添加">
  		</form>
  		
  <body>
    	
  </body>
</html>
