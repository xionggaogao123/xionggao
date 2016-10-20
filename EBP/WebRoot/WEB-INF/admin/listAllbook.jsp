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
	<title>管理书籍页面</title>
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
<script type="text/javascript">
	function checkAll(){
		var str = $("#bookname").val().trim();
		if(str =="" ||str==null){
			alert("输入不能为空");
			return false;
		}else{
			window.location.href="servlet/AdminServlet?method=getBook";
			return true;
		}
	}
</script>
	<style type="text/css">
		a{
			text-decoration: none;
		}
	</style>

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

		   <!-- 管理项对应的内容 -->
		   <!-- 内容二:书籍管理 -->
		  <div class="manager-content2">
		  		
          		 <div class="panel-heading">
               		   书籍管理
                <div style="float: right;">
               		 <button class="btn btn-success addrepertory"><a href="admin/AddBookPre.action">添加</a><span class="glyphicon glyphicon-plus"></span></button>
                </div>

                <div class="col-md-4" style="float: right;  margin-bottom:10px;">
                <form action="admin/FindBook.action" method="post" onsubmit="return checkAll()">
                   <div class="input-group">
                      <input type="text" class="form-control" id="bookname" name="bookname" placeholder='书名' >
                      <span class="input-group-btn">
                      <button class="btn btn-info" type="submit">
                      		  搜索
                      </button>
                      </span>
                  </div>
               </form>
                </div>
           </div>
          		
          		<c:if test="${ not empty pageBean.list }">
          		
          		<div class="content-collection" style="border-top:1px solid #DBDBDB;">
                  <div class="content-top">
                  <c:forEach items="${pageBean.list}" var="book">
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
	
	                          <div class="bt">
	                            <button  class="btn btn-info btn-sm change-btn"><a href="admin/UpdateBook.action?id=${book.id}">修改</a></button>
	                          </div>
	                        </div>
                       </div>
		  		</div>
		  		</c:forEach>
		  	</div>
		   	
			 <div class="person-bottom" style="padding-top: 50px; float: right;">
					<p align="center">共${pageBean.totalPage}页  
			    		<a href="javascript:void(0)" onclick="gotoPage(${pageBean.previousPage})">上一页</a>
			    		
			    		<c:forEach items="${pageBean.pageBar }" var="pageNum">
			    			<a href="javascript:void(0)" onclick="gotoPage(${pageNum})">
								<c:if test="${pageBean.currentPage ==  pageNum}">
									<font color="red">${pageBean.currentPage}</font>
								</c:if>
								<c:if test="${pageBean.currentPage ne pageNum}">
									${pageNum}
								</c:if>
							</a>
			    		</c:forEach>
			    		<a href="javascript:void(0)" onclick="gotoPage(${pageBean.nextPage})">下一页</a>
			    		转到第<input type="text" id = "willPage" value ="${pageBean.currentPage }" style="width: 30px">页
			    		   <input type="button" value="GO" onclick='gotoPage(document.getElementById("willPage").value)' />
			    		共${pageBean.totalRecord}条记录
		    	 </p>
    	 	</div>
  		</div>
  		</c:if>
  		 <c:if test="${empty pageBean.list }">
    	 	<h3 style="color:red;margin-left:350px">图书表中没有数据</h3>
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
	 		//2：验证val在一个合法的范围内 ：1 - totalPage
			var pageNo = parseInt(currentPage);
			if(pageNo<1 || pageNo >parseInt("${pageBean.totalPage}")){
				alert("输入的是不合法的页码");
				document.getElementById("willPage").value = "";
				return;
			};
  			window.location.href="admin/GetAllBook.action?currentPage="+currentPage;
  		};
  		
  </script>
</html>
