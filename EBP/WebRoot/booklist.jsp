<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'paysuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<title>购物车</title>
	<link rel="icon" href="image/icon.png">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/duokan.css">
	<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<style type="text/css">
    .breadnav {
        font-size: 16px;
        font-family: 楷体;
      }
    .breadnav > span + span:before {
        color: #CCCCCC;
        content: "> ";
        padding: 0 5px;
    }
    .shopping{
      width: 960px;
      margin: 30px auto;
    }
    .shopping .numBtn{
      float: left;
    }
    .body-content3 .shopping input{
      border: 1px solid #DBDBDB;
      outline-style: inherit;
      text-align: center;
    }
    .pay{
    	height: 200px;
    }

	</style>
	<script type="text/javascript">

	</script>
</head>
<body>
   <!-- 头部分 -->
	    <div class="headbox">
	    	<div class="navbox">
	    		<div class="nav-left">
			    	 <ul class="lt-list">
			             <li class="lt-li"><a class="nav-a" href="#" target="_blank">小米网</a></li>
			             <li class="part">|</li>
			             <li class="lt-li"><a class="nav-a" href="#" target="_blank">MIUI</a></li>
			             <li class="part">|</li>
			             <li class="lt-li"><a class="nav-a" href="#" target="_blank">米聊</a></li>
			             <li class="part">|</li>
			             <li class="lt-li"><a class="nav-a" href="#" target="_blank">游戏</a></li>
			             <li class="part">|</li>
			             <li class="lt-li"><a class="nav-a checked" href="#" target="_blank">多看阅读</a></li>
			             <li class="part">|</li>
			             <li class="lt-li"><a class="nav-a" href="#" target="_blank">云服务</a></li>
			             <li class="part">|</li>
			             <li class="lt-li"><a class="nav-a" href="#" target="_blank">小米网移动版</a></li>
			         </ul>
	    		</div>

	    		<div class="nav-right">
	    			<ul class="rt-list">
	    			   	<c:if test="${user == null}">
	    					<li class="rt-li"><a href="login.jsp">登录</a>/<a href="register.jsp">注册</a></li>
	    				</c:if>
	    				<c:if test="${user != null }">
	    					<li class="rt-li">${user.username}</li>
	    					<li class="part">|</li>
	    				<li class="rt-li exit"><a href="user/Exit.action">注销</a></li>
	    				</c:if>
	    				<li class="part">|</li>
	    				<li class="rt-li shop"><a href="shoppingcart.jsp">购物车</a></li>
	    				<li class="part">|</li>
	    				<li class="rt-li myPerson"><a href="vip/personalCenter.jsp">个人中心</a></li>
	    			</ul>
	    		</div>
	    	</div>
	    </div>

	    <!-- 主体部分 -->
	    <div class="mbody">
	        <!-- 主体搜索部分 -->
	    	<div class="body-top">
	    		<div class="logo">
	    			 <img src="image/logo.png">
	    		</div>

	    		<div class="serch">
	    			<input class="txt" type="text" placeholder="搜索书名或作者..." id="searchValue">
	    			<input class="butn" type="button" onclick="searchBook()">
	    		</div>
		    </div>

	       <!-- 主导航部分 -->
		    <div class="main-nav">
		         	<ul class="center-nav">
				       	<li><a href="index.jsp">首页</a></li>
				        <li><a href="#">榜单</a></li>
				        <li><a href="#">书评</a></li>
				        <li><a href="#">精品.免费</a></li>
				        <li><a href="#">分类</a></li>
				        <li><a href="#">客户端</a></li>
				        <li><a href="#">论坛</a></li>
				    </ul>
		    </div>

	<div class="breadnav">
	    <span>当前位置&nbsp;:&nbsp;首页</span>
	    <span style='color: #CCCCCC;'>搜索结果</span>
	</div>

		<div class="shopping">
		      <div class="panel  panel-warning">
				  
				<c:choose>
		    	  <c:when test="${empty list}">
		    		<h2>没有找到该书籍<a href="index.jsp">回到首页</a></h2>
		    	  </c:when>
		    	<c:otherwise>
					  <table class="table table-hover" border="1px" width="600px">
			   	    	<thead>
			   	    		<tr>
			   	    			<th>书名</th>
			   	    			<th>作者</th>
			   	    			<th>描述</th>
			   	    			<th>原价</th>
			   	    			<th>现价</th>
			   	    			<th>库存数量</th>
			   	    			<th></th>
			   	    			<th></th>
			   	    		</tr>
			   	    	</thead>
			   	    	<tbody>
			   	    	    <c:forEach items="${list}" var="list">
			   	    		<tr>
			   	    			<td><a href="book/BookDetail.action?id=${list.id}">${list.name}</a></td>
			   	    			<td>${list.author }</td>
			   	    			<td>${list.descration }</td>
			   	    			<td>${list.oldPrice}</td>
			   	    			<td>${list.newPrice }</td>
			   	    			<td>${list.amount }</td>
			   	    			<td><button  class="btn btn-info btn-sm" onclick="paymoney('${list.id}')">立即购买</button></td>
			   	    			<td><button class="btn btn-info btn-sm" onclick="shoppingcart('${list.id}')">加入购物车</button></td>
			   	    		</tr>
			   	    		</c:forEach>
			   	    	</tbody>
			   	    </table>
				  </c:otherwise>
				 </c:choose> 
				  
								
						   
			</div>
		</div>
   </div>

</body>
<script type="text/javascript">

		function searchBook(){
			var value=$("#searchValue").val();
			if((value=="")){
	
			}else{
				window.location.href="book/SelectBookByNameAndAuthor.action?value="+value;
			}
			
		}
		function paymoney(id){
			window.location.href="book/PayMoney.action?id="+id;
		
		}
		function shoppingcart(id){
			$.ajax({
				url:"ShoppingCartJsp.jsp?add="+id,
				type:'GET',
				dataType:'text',
				success:function (msg) {
					alert("此商品已加入购物车");
				}
			});
		}

	</script>
</html>
