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
    
    <title>My JSP 'shouye.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
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
	
			    	<!-- 右侧盒子:循环滚动图片浏览 -->
			    	<div id="big_banner_pic_wrap">
			    	    <!-- 轮播插件 -->
						<!--
							容器:carousel slide
							自动轮播:data-ride="carousel"
						 -->
						 <!-- 保证容器大小和图片要一致  否则出现问题 -->
						<div id="myCarousel" class="carousel slide" data-ride="carousel" style="width:955px">
							<!-- 分页控件 -->
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" class="active" data-slide-to="0"></li>
								<li data-target="#myCarousel" data-slide-to="1"></li>
								<li data-target="#myCarousel" data-slide-to="2"></li>
								<li data-target="#myCarousel" data-slide-to="3"></li>
								<li data-target="#myCarousel" data-slide-to="4"></li>
								<li data-target="#myCarousel" data-slide-to="5"></li>
							</ol>
							<!-- 图片内容 <-->
							<div class="carousel-inner">
								<div class="item active">
									<a href="#"><img src="image/scr1.jpg" alt="第一张"></a>
								</div>
								<div class="item">
									<a href="#"><img src="image/scr2.jpg" alt="第二张"></a>
								</div>
								<div class="item">
									<a href="#"><img src="image/scr3.jpg" alt="第三张"></a>
								</div>
								<div class="item">
									<a href="#"><img src="image/scr4.jpg" alt="第四张"></a>
								</div>
								<div class="item">
									<a href="#"><img src="image/scr5.jpg" alt="第五张"></a>
								</div>
								<div class="item">
									<a href="#"><img src="image/scr6.jpg" alt="第六张"></a>
								</div>
							</div>
							<!-- data-slide:上下图片的切换 -->
							<a href="#myCarousel" data-slide="prev" class="carousel-control left"><span class="glyphicon glyphicon-chevron-left"></span></a>
							<a href="#myCarousel" data-slide="next" class="carousel-control right"><span class="glyphicon glyphicon-chevron-right"></span></a>
	
						</div>
	
						<!-- 重磅推荐 -->
	                    <div class="choose-title">重磅推荐</div>
		                <div class="collection-img">
	                    	<c:forEach items="${listBook[0]}" var="three">
	                    		<div class="collection right">
			                        <div class="left-img">
			                    		<img src="${three.filename}">
				                    	<p class="price">
				                    		<span>￥${three.newPrice}</span>
				                    		<del>￥${three.oldPrice }</del>
				                    	</p>
			                        </div>
		
			                    	<div class="right-button">
			                    	    <div class="information">
				                    	    <p><a href="book/BookDetail.action?id=${three.id}">${three.name}</a></p>
				                    		<p>${three.descration}</p>
			                    	    </div>
		
			                    		<div class="bt">
			                    			<button  class="btn btn-info btn-sm" onclick="paymoney('${three.id}')">立即购买</button>
			                    			<button class="btn btn-info btn-sm" onclick="shoppingcart('${three.id}')">加入购物车</button>
			                    		</div>
			                    	</div>
		                       </div> 
	                    	</c:forEach>
	
		                    <div class="new-flash">
				               	<p>活动快讯</p>
				               	<ul>
				               		<li>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</li>
				               		<li><a href="#">超级畅销的好书特价啦，戳此看看～</a></li>
				               	</ul>
		                    </div>
		
		
		                </div>
					</div>
		        </div>
	
		        <!-- 主体content部分 -->
		        <div class="content">
		            <!-- 左侧广告栏 -->
		            <div class="poster">
		            	<ul class="poster-list">
		            		<li><img src="image/content1.jpg"></li>
		            		<li><img src="image/content2.jpg"></li>
		            		<li><img src="image/content3.jpg"></li>
		            	</ul>
		            </div>
	
		            <!-- 右侧书籍展示 -->
		            <div class="content-collection">
		                <div class="content-top">
		                    <div class="collection-img">
		                        <c:forEach items="${listBook[1]}" var="six">
		                    		<div class="collection right">
				                        <div class="left-img">
				                    		<img src="${six.filename}">
					                    	<p class="price">
					                    		<span>￥${six.newPrice}</span>
					                    		<del>￥${six.oldPrice }</del>
					                    	</p>
				                        </div>
			
				                    	<div class="right-button">
				                    	    <div class="information">
					                    	    <p><a href="book/BookDetail.action?id=${six.id}">${six.name}</a></p>
					                    		<p>${six.descration}</p>
				                    	    </div>
			
				                    		<div class="bt">
				                    			<button  class="btn btn-info btn-sm" onclick="paymoney('${six.id}')">立即购买</button>
				                    			<button class="btn btn-info btn-sm" onclick="shoppingcart('${six.id}')">加入购物车</button>
				                    		</div>
				                    	</div>
			                       </div> 
		                    	</c:forEach>
		        			</div>
		        		</div>
	        		</div>
	   			</div>
	   		
	   		
	   		</div>
   		</div>
  
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
		function shoppingcart(id){
			$.ajax({
				url:"ShoppingCartJsp.jsp?add="+id,
				type:'GET',
				dataType:'text',
				success:function(){
					alert("此商品成功加入购物车");
				}
			});
			
		}
	</script>
</html>