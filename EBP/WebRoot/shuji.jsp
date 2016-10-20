<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Random"%>
<%@ page import="java.security.*" %>
<%@ page import="com.sun.org.apache.xml.internal.security.utils.Base64" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shuji.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<title>多看阅读购物车</title>
	<link rel="icon" href="image/icon.png">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/duokan.css">
	<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
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
				       	<li class="checked"><a href="index.jsp">首页</a></li>
				        <li><a href="#">榜单</a></li>
				        <li><a href="#">书评</a></li>
				        <li><a href="#">精品.免费</a></li>
				        <li><a href="#">分类</a></li>
				        <li><a href="#">客户端</a></li>
				        <li><a href="#">论坛</a></li>
				    </ul>
		    </div>

        <!-- 主体内容一:首页内容 -->
        <div class="body-content1">

		    <!-- 主体banner部分 -->
		    <div class="banner">
		         <!-- 左侧盒子:小说分类导航 -->
		 <div id="big_banner_wrap">
			         <ul id="banner_menu_wrap">
					      <li class="active">
			                 <a>小说&nbsp;成功励志</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -20px;">
			                      <ul class="banner_menu_content_ul">
		                         <li><span>言情</span><a href="book/SelectSameTypeBook.action?id=11"><span>选购</span></a></li>
		                         <li><span>悬疑推理</span><a href="book/SelectSameTypeBook.action?id=12"><span>选购</span></a></li>
		                         <li><span>科幻</span><a href="book/SelectSameTypeBook.action?id=13"><span>选购</span></a></li>
		                         <li><span>世界名著</span><a href="book/SelectSameTypeBook.action?id=14"><span>选购</span></a></li>
		                         <li><span>中国名著</span><a href="book/SelectSameTypeBook.action?id=15"><span>选购</span></a></li>
		                         <li><span>近现代</span><a href="book/SelectSameTypeBook.action?id=16"><span>选购</span></a></li>
		                         <li><span>职场</span><a href="book/SelectSameTypeBook.action?id=17"><span>选购</span></a></li>
		                         <li><span>为人处世</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
		                         <li><span>个人修养</span><a href="book/SelectSameTypeBook.action?id=19"><span>选购</span></a></li>

		                      
		                     </ul>
			                    
				                 </div>
			             </li>
	
			             <li>
			                 <a>文学&nbsp;原创文学</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -75px;">
			                     <ul class="banner_menu_content_ul">
			                      <li><span>文学理论</span><a href="book/SelectSameTypeBook.action?id=21"><span>选购</span></a></li>
			                      <li><span>影视文学</span><a href="book/SelectSameTypeBook.action?id=22"><span>选购</span></a></li>
			                      <li><span>文学评论与鉴赏</span><a href="book/SelectSameTypeBook.action?id=23"><span>选购</span></a></li>
			                      <li><span>民间文学</span><a href="book/SelectSameTypeBook.action?id=24"><span>选购</span></a></li>
			                      <li><span>戏剧与曲艺</span><a href="book/SelectSameTypeBook.action?id=25"><span>选购</span></a></li>
			                      <li><span>散文随笔</span><a href="book/SelectSameTypeBook.action?id=26"><span>选购</span></a></li>
			                      
			                     </ul>
			                    
				                 </div>
			             </li>
	
			             <li>
			                 <a>人文社科&nbsp;自然科学</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -130px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>哲学与宗教</span><a href="book/SelectSameTypeBook.action?id=31"><span>选购</span></a></li>
			                     <li><span>社会学</span><a href="book/SelectSameTypeBook.action?id=32"><span>选购</span></a></li>
			                     <li><span>国学</span><a href="book/SelectSameTypeBook.action?id=33"><span>选购</span></a></li>
			                     <li><span>语言文学</span><a href="book/SelectSameTypeBook.action?id=34"><span>选购</span></a></li>
			                     <li><span>文化</span><a href="book/SelectSameTypeBook.action?id=35"><span>选购</span></a></li>
			                     <li><span>教育</span><a href="book/SelectSameTypeBook.action?id=36"><span>选购</span></a></li>
			                     
			                     </ul>
			                    
			                 </div>
			             </li>
	
			             <li>
			                 <a>生活&nbsp;杂志</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -185px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>美食</span><a href="book/SelectSameTypeBook.action?id=41"><span>选购</span></a></li>
			                     <li><span>养生保健</span><a href="book/SelectSameTypeBook.action?id=42"><span>选购</span></a></li>
			                     <li><span>亲子家教</span><a href="book/SelectSameTypeBook.action?id=43"><span>选购</span></a></li>
			                     <li><span>家居休闲</span><a href="book/SelectSameTypeBook.action?id=44"><span>选购</span></a></li>
			                     <li><span>新闻人物</span><a href="book/SelectSameTypeBook.action?id=45"><span>选购</span></a></li>
			                     <li><span>商业财经</span><a href="book/SelectSameTypeBook.action?id=46"><span>选购</span></a></li>
			                        
			                     </ul>
			                 </div>
			             </li>
	
			             <li>
			                 <a>动漫绘本</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -240px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>幽默</span><a href="book/SelectSameTypeBook.action?id=51"><span>选购</span></a></li>
			                     <li><span>轻小说</span><a href="book/SelectSameTypeBook.action?id=52"><span>选购</span></a></li>
			                     <li><span>卡通画</span><a href="book/SelectSameTypeBook.action?id=53"><span>选购</span></a></li>
			                     <li><span>插图本</span><a href="book/SelectSameTypeBook.action?id=54"><span>选购</span></a></li>
			                     <li><span>设计</span><a href="book/SelectSameTypeBook.action?id=55"><span>选购</span></a></li>
			                     <li><span>书法</span><a href="book/SelectSameTypeBook.action?id=56"><span>选购</span></a></li>
			                     <li><span>摄影</span><a href="book/SelectSameTypeBook.action?id=57"><span>选购</span></a></li>
			                     <li><span>建筑</span><a href="book/SelectSameTypeBook.action?id=58"><span>选购</span></a></li>
			                     <li><span>舞蹈</span><a href="book/SelectSameTypeBook.action?id=59"><span>选购</span></a></li>
			                         
			                     </ul>
			                 </div>
			             </li>
	
			             <li>
			                 <a>政治&nbsp;军事</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -295px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>政治军事</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>政治理论</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>中国共产党</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>外交、国际关系</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>马克思主义理论</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>中国政治</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                        
			                     </ul>
			                 </div>
			             </li>
	
			             <li>
			                 <a>历史</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -350px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>中国总史</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>大洋洲史</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>地方志史</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>风俗习惯</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>文物考古</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>史学理论</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                        
			                     </ul>
			                 </div>
			             </li>
	
			             <li>
			                 <a>少儿</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -405px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>幼儿启蒙</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>儿童文学</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>科普百科</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>少儿英语</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>绘画书法</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>智力游戏</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                       
			                     </ul>
			                    
			                 </div>
			             </li>
	
			             <li>
			                 <a>进口原版</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -460px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>艺术与摄影</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>Children's Books(童书)</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                         
			                     </ul>
			                 </div>
			             </li>
	
	
			             <li>
			                 <a>艺术</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -515px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>书法</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>摄影</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>建筑</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>舞蹈</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                        
			                     </ul>
			                 </div>
			             </li>
	
	
	
			             <li>
			                 <a>传记&nbsp;其它</a>
			                 <a class="banner_menu_i carousel-control right">&rsaquo;</a>
			                 <div class="banner_menu_content" style="width: 300px; top: -570px;">
			                     <ul class="banner_menu_content_ul">
			                     <li><span>历代帝王</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>科学家</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>学者</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>人物合集</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                     <li><span>领袖首脑</span><a href="book/SelectSameTypeBook.action?id=18"><span>选购</span></a></li>
			                        
			                     </ul>
			                 </div>
			             </li>
	
			         </ul>
	                </div>



	        <!-- 主体content部分 -->
	        <div class="content">
	          
	            <!-- 右侧书籍展示 -->
	            <div class="content-collection">
	                <div class="content-top">
	                    <div class="collection-img">


                      <c:forEach items="${pageBean.list}" var="list">
								 <div class="collection left">
	                        		<div class="left-img">
			                    		<img src="${list.filename}">
				                    	<p class="price">
				                    		<span>￥${list.newPrice}</span>
				                    		<del>￥${list.oldPrice}</del>
				                    	</p>
	                        		</div>

	                    		<div class="right-button">
	                    	    	<div class="information">
		                    	    	<p><a href="book/BookDetail.action?id=${list.id}">${list.name}</a></p>
		                    			<p>${list.descration}</p>
	                    	   		</div>

	                    		<div class="bt">
	                    			<button  class="btn btn-info btn-sm" onclick="paymoney('${list.id}')">立即购买</button>
	                    			<button class="btn btn-info btn-sm" onclick="shoppingcart('${list.id}')">加入购物车</button>
	                    		</div>
	                    		</div>
                      			 </div>	
						   </c:forEach>
						   
		

	             

	        </div>
       				</div>

          <!--  页面切换按钮  -->
				    <div class="person-bottom" style="padding-top: 30px; float: right; width:900px;">
				    	 <button class="btn btn-default" onclick="gotoPage(${pageBean.previousPage})">&lt;&nbsp;上一页</button>
				        <c:forEach items="${pageBean.pageBar}" var="pageNum">
				        <button  class="btn btn-default" onclick="gotoPage(${pageNum})">
						
						<c:if test="${pageBean.currentPage ==  pageNum}">
							<font color="red">${pageBean.currentPage}</font>
						</c:if>
						<c:if test="${pageBean.currentPage ne  pageNum}">
							${pageNum}
						</c:if>
						</button>
						
						</c:forEach>
				        <button class="btn btn-default" onclick="gotoPage(${pageBean.nextPage})">下一页&nbsp;&gt;</button>
				        <p class='bottom-txt' style=''>共${pageBean.totalPage}页  共${pageBean.totalRecord}条记录  </p>
				        <span class="bottom-txt">到第<input type="text" value="${pageBean.currentPage}" id="currentPage" style="width: 40px; height: 35px; text-align: center;">页</span>
				        <button class="btn btn-default" onclick='gotoPage(document.getElementById("currentPage").value)'>确定</button>
				        
				         
	       				
				    </div>
				</div>
			</div>
			<%
	MessageDigest md=null;
	try {
		md = MessageDigest.getInstance("md5");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	String input=System.currentTimeMillis()+new Random().nextInt(1000000)+"";
	byte[] data=md.digest(input.getBytes());
	String token=Base64.encode(data);
	HttpSession ses = request.getSession();
	ses.setAttribute("token", token);
%>
	<input type="hidden" name="hidden" value=<%=token %> id="hidden">
   </body>
<script type="text/javascript" src="js/duokan.js"></script>
<script type="text/javascript">

		function searchBook(){
			var value=$("#searchValue").val();
			if(value==""){
					
			}else{
				window.location.href="book/SelectBookByNameAndAuthor.action?value="+value;
			}
			
		}

		function paymoney(id){
			window.location.href="book/PayMoney.action?id="+id;

		}
		function gotoPage(currentPage){
	
			window.location.href="book/SelectSameTypeBook.action?currentPage=" + currentPage;
		}
		function shoppingcart(id){
			$.get("ShoppingCartJsp.jsp?add="+id,function(map1){
				alert("此商品成功加入购物车");
			},'json');
			
			
		}
	</script>
</html>
