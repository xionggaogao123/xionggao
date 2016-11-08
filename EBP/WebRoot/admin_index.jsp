<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
    String username=(String)session.getAttribute("admin_username");
    if (username==null){
          response.sendRedirect("adminLogin.jsp");
      }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
	<title>管理员页面</title>
    <link rel="icon" href="image/icon.png">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/manager.css">
	<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript">
	  function setDateTime() {
		var date = new Date();
		var day = date.getDay();
		var week;
		switch (day) {
		case 0:
			week = "星期日";
			break;
		case 1:
			week = "星期一";
			break;
		case 2:
			week = "星期二";
			break;
		case 3:
			week = "星期三";
			break;
		case 4:
			week = "星期四";
			break;
		case 5:
			week = "星期五";
			break;
		case 6:
			week = "星期六";
			break;
		}
		var today = date.getFullYear() + "年" + (date.getMonth() + 1) + "月"
				+ date.getDate() + "日  " + week + " " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds();
		document.getElementById("today").innerHTML = today;
	}
	window.setInterval("setDateTime()", 1000);
</script>
  </head>
  
<body>
  <!-- 头部分 -->
      <div class="headbox">
      	<div class="left" style="float:left;height: 35px;line-height:35px;margin-left:20px"><i class="icon-time"></i>&nbsp;&nbsp;<font id="today"></font></div>
          <div class="nav-right">
            
            <ul class="rt-list">
              <li class="rt-li"><a onclick="check()"><i class="icon-user"></i>&nbsp;退出系统</a></li>
              <li class="rt-li-right"><font color="red">欢迎管理员：<%=session.getAttribute("admin_username")%></font></li>
            </ul>
          </div>
      </div>

  <!-- 搜索导航部分 -->
      <!-- 主体搜索部分 -->
    <div class="mbody">
        <div class="body-top">
          <div class="logo">
             <img src="image/logo.png">
          </div>
        </div>


         <!-- 主导航部分 -->
        <div class="main-nav">
              <ul class="center-nav">
                <li class="checked"><a href="manager.html">首页</a></li>
                <li><a href="#">榜单</a></li>
                <li><a href="#">书评</a></li>
                <li><a href="#">精品.免费</a></li>
                <li><a href="#">分类</a></li>
                <li><a href="#">客户端</a></li>
                <li><a href="#">论坛</a></li>
            </ul>
        </div>
    </div>



	<div class="manager-body body1">
	   <!-- 管理导航项 -->
		 <div class="manager-nav">
		 	 <ul class="nav nav-pills nav-stacked">
		 	 	<li><h3>管理项</h3></li>
		 	 	<li class="act" value="1"><a href="servlet/AdminServlet?method=getAllUserListByPage">用户管理</a></li>
		 	 	<li value="2"><a href="servlet/AdminServlet?method=getBookByPage">书籍管理</a></li>
		 	 	<li value="3"><a href="servlet/AdminServlet?method=getAllUserOrder">订单管理</a></li>
		 	 </ul>
		 </div>

		   <!-- 管理项对应的内容 -->
		   <!-- 内容一:用户管理 -->
		  <div class="manager-content1">
	 			
		  </div> 
			
	</div>

</body>
<script type="text/javascript" src="js/manager.js"></script>
<script type="text/javascript">
	 
</script>
</html>
