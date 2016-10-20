<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'turn.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<title></title>

	<script type="text/javascript">
	var secs= 3;
	var URL;
	function Load(url){
		URL = url;
		for(var i=secs;i>=0;i--){
			window.setTimeout('doUpdate('+i+')',(secs-i)*1000);
		} 
	}
	function doUpdate(num){
		document.getElementById('ShowDiv').innerHTML='将在'+num+'秒后自动跳转到登录页面';
		if(num==0){window.location=URL;}
	}
	</script>

  </head>
  
  <body onload="Load('login.jsp')">
	<div id="ShowDiv">
		
	</div>
</body>
</html>
