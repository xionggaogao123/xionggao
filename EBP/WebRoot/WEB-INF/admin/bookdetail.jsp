<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	
	 function check() {
	      if(confirm("您确定要退出吗?")){
		        window.location.href = "adminLogin.jsp";
	           }
	       }
	       
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
                <li class="checked"><a href="javascript:void(0)">首页</a></li>
                <li><a href="javascript:void(0)">榜单</a></li>
                <li><a href="javascript:void(0)">书评</a></li>
                <li><a href="javascript:void(0)">精品.免费</a></li>
                <li><a href="javascript:void(0)">分类</a></li>
                <li><a href="javascript:void(0)">客户端</a></li>
                <li><a href="javascript:void(0)">论坛</a></li>
            </ul>
        </div>
    </div>



	<div class="manager-body body1">
	   <!-- 管理导航项 -->
		 <div class="manager-nav">
		 	 <ul class="nav nav-pills nav-stacked">
		 	 	<li><h3>管理项</h3></li>
		 	 	<li  value="1"><a href="admin/GetAllUser.action">用户管理</a></li>
		 	 	<li class="act" value="2"><a href="admin/GetAllBook.action">书籍管理</a></li>
		 	 	<li value="3"><a href="admin/GetAllOrders.action">订单管理</a></li>
		 	 </ul>
		 </div>
	
	
		 <div class="manager-content1">
		 <div class="panel-heading"><h3 style="color:green;margin-left:400px">书籍详情</h3></div>
				<c:if test="${not empty book }">
				<%-- <c:forEach items="${book }" var="book"> --%>
					<div class="collection-img">
	                        <div class="collection left">
	                          <div class="left-img">
		                           <img src="${book.filename }"> 
		                          <p class="price">
		                            <span>￥${book.newPrice }</span>
		                            <del>￥${book.oldPrice }</del>
		                            <span>${book.amount }本</span>
		                          </p>
	                          </div>

	                        <div class="right-button">
	                            <div class="information">
	                              <p><a href="javascript:void(0)">${book.name }</a></p>
	                            <p>${ book.descration}</p>
	                            </div>
	
	                         
	                        </div>
                       </div>
		  		</div>
		  	<%-- 	</c:forEach> --%>
				</c:if>
				
			<c:if test="${empty book }">
        		<center><font color="red" size="+1">对不起！没有找到对应的的书籍....</font></center>
			</c:if>	
		 </div>
		   



	</div>


		

</body>
<script type="text/javascript" src="js/manager.js"></script>
<script type="text/javascript">
	 
	 	function gotoPage(currentPage){
	 		if(parseInt(currentPage) !=currentPage){
	 			alert("输入不符合规范,请重新输入有效的数字");
	 			document.getElementById("willPage").value = "";
	 			return;
	 		}
	 		
  			window.location.href="servlet/UserServlet?method=getAllUserListByPage&currentPage="+currentPage;
  		}
  		
  </script>
</html>
