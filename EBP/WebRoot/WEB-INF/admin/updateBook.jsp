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

	
	<style type="text/css">
		.update-content{
			margin-left:300px;
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
		 	 	<li class="" value="1"><a href="admin/GetAllUser.action">用户管理</a></li>
		 	 	<li class="act" value="2"><a href="admin/GetAllBook.action">书籍管理</a></li>
		 	 	<li value="3"><a href="admin/GetAllOrders.action">订单管理</a></li>
		 	 </ul>
		 </div>

		   <!-- 管理项对应的内容 -->
		   <!-- 内容二:书籍管理 -->
		  <div class="manager-content2">
		  		
          		<div class="panel-heading" style="">
          			<h3 style="align:center;margin-left:400px;color:green">更新图书</h3>
          		</div>
          	<div class="update-content">
          	<form action="admin/UpdateSuccBook.action?id=${book.id}" method="post" onsubmit=" return validate()">
		   	    <div class="collection-img">
		   	    	<c:if test="${not empty book }">
		   	    		<div class="collection left">
	                          <div class="left-img">
		                           <img style="width:100px;height:100px" src="${book.filename }">
		                           <input type="hidden" name="filename" value="${book.filename}"/>
		                          <p class="price">
		                            <span>现价：￥<input type="text" name="newPrice" id="newPrice" size="1px" value="${book.newPrice}"></span>
		                            <p style="color:red">${errors.newPrice }</p>
		                            <span>原价：<del>￥<input type="text" name="oldPrice" id="oldPrice" size="1px" value="${book.oldPrice }"></del></span>
		                            <p style="color:red">${errors.oldPrice }</p>
		                            <span>数量：&nbsp;&nbsp;&nbsp;<input type="text" id="amount" name="amount" size="1px" value="${book.amount}">本</span>
		                            <p style="color:red">${errors.amount }</p>
	                          </div>

	                        <div class="right-button">
	                            <div class="information">
	                              <p>书名：<a ><input type="text" size="7px"name="name" id="bookName" value="${book.name }"></a></p>
	                              <p>作者：<a><input type="text" size="5px" name="author" id="bookAuthor" value="${book.author }"></a>
	                              <p>描述信息：<input type="text" name="descration" size="20px" id="bookDescration" value="${ book.descration}"></p>
	                            </div>
	                            
	                            <div class="" style="margin-top:20px">
	                         		 <button type="submit" class="btn btn-info"  >确定更新</button>
	                   			</div>
	                        </div>
                       </div>
		   	    	</c:if>
		   	    </div>
		   	</form>
		</div>
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
  			window.location.href="servlet/AdminServlet?method=getBookByPage&currentPage="+currentPage;
  		}
  		
  </script>
  
  <script type="text/javascript"> 
	var newPrice = document.getElementById("newPrice").value;
	var oldPrice = document.getElementById("oldPrice").value;
	var amount = document.getElementById("amount").value;
	
	
	
     function validate(){ 
    	 
       var reg = new RegExp("^\\d+(\\.\\d+)?$");  
       var strnewPrice = document.getElementById("newPrice").value;
       var stroldPrice = document.getElementById("oldPrice").value;
       var stramount = document.getElementById("amount").value;
       
       var name = document.getElementById("bookName").value;
   		var author = document.getElementById("bookAuthor").value;
   		var descration = document.getElementById("bookDescration").value;
   	
       if(name ==""||name==null){
    	   alert("标题不能为空");
    	   return false;
       }
       if(author ==""||author==null){
    	   alert("作者不能为空");
    	   return false;
       }
       if(descration ==""||descration==null){
    	   alert("描述不能为空");
    	   return false;
       }
       
       if(!reg.test(strnewPrice)){  
         alert("请输入合法金额!");
         document.getElementById("newPrice").value = newPrice;
         return false;
       }  
       if(!reg.test(stroldPrice)){  
         alert("请输入合法金额!"); 
         document.getElementById("oldPrice").value = oldPrice;
         return false;
       }  
       if(!reg.test(stramount)){  
         alert("请输入合法数量!");  
         document.getElementById("amount").value = amount;
         return false;
       }
       window.location.href="servlet/AdminServlet?method=update&id=${book.id}";
       return true;
  }  
</script>
</html>
