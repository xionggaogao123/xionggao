<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list_employee.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript">
		
	 $(function(){
		//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
		$(".delete").click(function(){
			var lastName = $(this).next(":hidden").val();
			var flag = confirm("确定要删除" + lastName + "的信息吗?");
			if(flag){
				var $tr = $(this).parent().parent();
				//删除, 使用 ajax 的方式
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url, args, function(data){
					//若 data 的返回值为 1, 则提示 删除成功, 且把当前行删除
					console.log(data);
					if(data == "1"){
						alert("删除成功!");
						$tr.remove();
					}else{
						//若 data 的返回值不是 1, 提示删除失败. 
						alert("删除失败!");
					}
				});	
			}
			
			//取消超链接的默认行为
			return false;
		});		
	}); 
	</script>

  </head>
  
  <body>
    	<c:if test="${ empty list }">没有任何信息</c:if>
    	<h3 align="center">员工信息</h3>
    	<c:if test="${not empty list}">
    		<table border="1" cellpadding="10" cellspacing="0" align="center" style="margin-top:50px">
    			<tr>
					<td>ID</td>
					<td>LASTNAME</td>
					<td>EMAIL</td>
					<td>CREATETIME</td>
					<td>DEPT</td>
					<td>DELETE</td>
					<td>EDIT</td>
				</tr>
				<c:forEach items="${list}" var="employee">
	   	    		<tr>
	   	    			<td>${employee.id }</td>
	   	    			<td>${employee.lastName }</td>
	   	    			<td>${employee.email }</td>
	   	    			<td>${employee.createTime }</td>
	   	    			<td>${employee.department.departmentName }</td>
	   	    			<td>
	   	    				<a href="${pageContext.request.contextPath }/deleteEmployee?id=${employee.id }" class="delete">删除</a>
	   	    				<input type="hidden" value="${employee.lastName}">
	   	    			</td>
	   	    		</tr>
				</c:forEach>
    		</table>
    	</c:if>
  </body>
</html>
