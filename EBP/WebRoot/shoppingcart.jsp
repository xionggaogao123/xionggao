<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.security.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shoppingcart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<html lang="en">

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

	</style>
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
	    <span style='color: #CCCCCC;'>购物车</span>
	</div>

	<div class="shopping">
	      <div class="panel  panel-warning">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			            购物车状态&nbsp;&nbsp;&nbsp;&nbsp;${notEnoughBook}
			        </h3>
			    </div>
				<div class="panel-body">
			  
				<c:choose>
		    	<c:when test="${empty shoppingcart}">
		    	<div>
		    		<h2>购物车为空....<a href="index.jsp">回到首页继续购物</a></h2>
		    	</div>
		    	</c:when>
		    	<c:otherwise>
		    	<div id="isone" style="display:none;">
		    		<h2>购物车为空....<a href="index.jsp">回到首页继续购物</a></h2>
		    	</div>
		    	
		    	<div id="istwo">
			    <c:forEach items="${shoppingcart}" var="list">
					<div class="panel panel-default" id="${list.book.id}">
					    <div class="panel-heading">
					        <h3 class="panel-title">
					           ${list.book.name}
					        </h3>
					    </div>
	    				<div class="panel-body">
					                <div class="order number1">
				                        <div class="col-md-1">
				                            <img src="${list.book.filename }" alt="" style="width: 64px;"/>
				                        </div>

				                        <div class="col-md-3">
				                            <span style="font-size: 16px;font-weight: bold;">${list.book.name}</span><br/>
				                            <span>${list.book.author}</span>
				                            <p style="color: gray; margin-top: 20px;">¥<span class='unit-price'>${list.book.newPrice}</span></p>
				                        </div>

				                        <div class="col-md-2 b">
				                        </div>

				                        <div class="col-md-3">
				                        	 <div class="btn-group" style="width:180px ">
				                               <span class="numBtn" style="font-size: 16px; line-height: 35px;">总件数:&nbsp;</span>
																		<div class="input-group" style="width: 100px;">
																		    <span class="input-group-btn">
																				<button class="btn btn-default" type="button" onclick="addnumber('${list.book.id}')">
																					+
																				</button>
																			</span>
																			<input type="text" class="form-control" style="width:60px;" value="${list.number}" onblur="change('${list.book.id}')">
																			<span class="input-group-btn">
																				<button class="btn btn-default" type="button" onclick="delnumber('${list.book.id}')">
																					-
																				</button>
																			</span>
																		</div>
				                              </div>
				                            <p style="margin-top: 20px;font-size: 16px;">小计:<span class='total-amount' id="total${list.book.id}"><fmt:formatNumber value="${list.number*list.book.newPrice}" pattern="#.##" /></span></p>
				                        </div>

				                        <div class="col-md-2" style="text-align: center;">
				                        	
				                        </div>

				                        <div class="col-md-1">
				                            <span class="glyphicon glyphicon-trash" onclick="del('${list.book.id}')"></span>
				                        </div>
				                    </div>
					   			 </div>
								</div>
							 </c:forEach>
									 <div style="text-align: center; float:right;height:50;width:300;margin-right:30px;">
				                        	<button class="btn btn-info"  style="float:right;" onclick="pay()">立即支付</button>
				                        	<span style="float:left;font-size:20px;color:black" id="totalMoney">总额:${total}</span>
				                        </div>	
					  </div>  	
		    	</c:otherwise>
		    	</c:choose>
				
 				
						        </li>
				            </ul>
					    </div>
				  </div>
			  </div>
		 </div>
   </div>
   		
</body>
<script type="text/javascript">

	function pay(){
		window.location.href="book/PayMoney.action?mark=true";
	}

	function change(id){
		var jiaoyan=$("#"+id+" input").val();
         var type = /^[0-9]*[0-9][0-9]*$/;
         var re = new RegExp(type);
         if (jiaoyan.match(re) == null) {
        	 $.ajax({
 				url:"ShoppingCartJsp.jsp?error="+id,
 				type:'GET',
 				dataType:'json',
 				success:function(map1){
 					 alert("请输入大于零的整数!"); 
 	                 $("#"+id+" input").prop("value",map1.yuan);
 				}
 			});
             return;
         }
         $.ajax({
				url:"ShoppingCartJsp.jsp?input="+id+";"+$("#"+id+" input").val(),
				type:'GET',
				dataType:'json',
				success:function(map1){
					 var h1 = document.getElementById("total" + id);
					 h1.innerHTML = map1.totalPrice;
					 var h2 = document.getElementById("totalMoney");
					 h2.innerHTML = "总额:"+map1.total;
				}
			});
	}

	function del(id){
		if(confirm("确定要删除该商品？")){
			 $.ajax({
					url:"ShoppingCartJsp.jsp?del="+id,
					type:'GET',
					dataType:'json',
					success:function(map1){
						if(map1.isEmpty=="yes"){
							$("#istwo").css("display","none");
							$("#isone").css("display","block");
							
						}
						$("#"+id).css("display","none");
						 var h2 = document.getElementById("totalMoney");
						 h2.innerHTML = "总额:"+map1.total;
					}
				});
		}	
	}
	
	function addnumber(id){
		 $.ajax({
				url:"ShoppingCartJsp.jsp?addnum="+id,
				type:'POST',
				dataType:'json',
				success:function(map1){
					 var h1 = document.getElementById("total" + id);
					 var number=parseInt($("#"+id+" input").val())+1;				
					 $("#"+id+" input").prop("value",number);
					 h1.innerHTML = map1.totalPrice;
					 var h2 = document.getElementById("totalMoney");
					 h2.innerHTML = "总额:"+map1.total;
				}
			});
	}
	function delnumber(id){
		 $.ajax({
				url:"ShoppingCartJsp.jsp?delnum="+id,
				type:'POST',
				dataType:'json',
				success:function(map1){
					if(parseInt($("#"+id+" input").val())==0){
						$("#"+id+" input").prop("value","0");
						var h1 = document.getElementById("total" + id);
						 h1.innerHTML ="0";
						}else{
							 var h1 = document.getElementById("total" + id);
							 var number=parseInt($("#"+id+" input").val())-1;				
							 $("#"+id+" input").prop("value",number);
							 h1.innerHTML = map1.totalPrice;
						}
						 var h2 = document.getElementById("totalMoney");
						 h2.innerHTML = "总额:"+map1.total;
				}
			});
	}
</script>
</html>
