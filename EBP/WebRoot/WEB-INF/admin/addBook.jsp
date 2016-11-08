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
		 	 	<li value="1"><a href="admin/GetAllUser.action">用户管理</a></li>
		 	 	<li class="act" value="2"><a href="admin/GetAllBook.action">书籍管理</a></li>
		 	 	<li  value="3"><a href="admin/GetAllOrders.action">订单管理</a></li>
		 	 </ul>
		 </div>
	
		 <div class="manager-content1">
				<div class="panel-heading">书籍添加</div>
					
					
					 <form action="admin/AddBook.action" method="post" enctype="multipart/form-data" onsubmit="return checkAll()">
					 	<p style="color:red;margin-left:200px">${errorsException }</p>
					 	  <div class="form-group">
						    <label  class="col-sm-2 control-label">图书编号</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="id" name="id" placeholder="请输入图书编号" value="${book.id }">
						    </div>
						  </div>
						  <p style="color:red;margin-left:200px">${errors.strid }</p>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">书名</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control"  id="name" name="name" placeholder="请输入书名" value="${book.name }">
						    </div>
						  </div>
						    <p style="color:red;margin-left:200px" ></p>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">作者</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="author" name="author" placeholder="请输入作者" value="${book.author }">
						    </div>
						  </div>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">描述</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="descration"  name="descration" placeholder="请输入描述信息" value="${book.descration }">
						    </div>
						  </div>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">原价</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control"  id="olpPrice" name="oldPrice" placeholder="请输入原价" value="${book.oldPrice }">
						    </div>
						  </div>
						   <p style="color:red;margin-left:200px">${errors.oldPrice }</p>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">现价</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="newPrice" name="newPrice" placeholder="请输入现价" value="${book.newPrice }">
						    </div>
						  </div>
						   <p style="color:red;margin-left:200px">${errors.newPrice}</p>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">数量</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="amount" name ="amount" placeholder="请输入数量" value="${book.amount }">
						    </div>
						  </div>
						   <p style="color:red;margin-left:200px">${errors.amount }</p>
						  <div class="form-group">
						    <label  class="col-sm-2 control-label">图片</label>
						    <div class="col-sm-10">
						      <input type="file" class="form-control" id="filename" name="filename">
						    </div>
						  </div>
						<div class="form-group">
						    <label for="name" class="col-sm-2 control-label">图书分类</label>
						    <label>书籍类型</label>
    							<select class="" style="width:20%" id="type" name ="type" onchange="show_subtype()">
    								<option value="0">--请选择--</option>
    								  <option value="1" >小说 成功励志</option>
								      <option value="2">文学 原创文学</option>
								      <option value="3">人文社科 自然科学</option>
								      <option value="4">生活 杂志</option>
								      <option value="5">动漫 绘本</option>
								      <option value="6">政治 军事</option>
								      <option value="7">历史</option>
								      <option value="8">进口原版</option>
								      <option value="9">艺术</option>
								      <option value="10">传记 其它</option>
    							</select>
    		
    						<label>详细分类</label>
    							<select class="" style="width:20%" id="subtype" name="" >
    								<option value="0">--请选择--</option>
    							</select>
  						</div>
						  
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-6">
						      <button type="submit" class="btn btn-default">添加</button>
						    </div>
						  </div>
					</form>
				
		 </div>
		 
		   



	</div>


		

</body>
<script type="text/javascript" src="js/manager.js"></script>

  <script type="text/javascript">
    	function show_subtype(){
    		var id = $('#type option:selected').val();
    		console.log(id);
    		 $('#subtype').empty();
			$('#subtype').append("<option value='0'>-请选择-</option>"); 
			
    		var url = "${pageContext.request.contextPath}/getCategory.jsp";
    		var args = {"id":id,"time":new Date()};
    		if(id!=""){
    			$.ajax({   //json对象
					url: url,
					data: args,
					dataType: "json",
					type: "post",
					success: successful,
					async: true,
					cache: false
				});
				function successful(data){
					for(var i in data){
						console.log(data[i].id + ", " + data[i].name);
						var subId = data[i].id;
						var subName = data[i].name;
						console.log(subId+"--"+subName);
						$('#subtype').append("<option value='"+subId+"'>"+subName+'</option>');
					}
				}
    		}
    	}
    </script>
    
     <script type="text/javascript">
    	function checkAll(){
    		var strId = document.getElementById("id").value;
    		var strName = document.getElementById("name").value;
    		var strAuthor = document.getElementById("author").value;
    		var strDescration = document.getElementById("descration").value;
    		var strOldPrice = document.getElementById("olpPrice").value;
    		var strNewPrice = document.getElementById("newPrice").value;
    		var strAmount = document.getElementById("amount").value;
    		var strFileName = document.getElementById("filename").value;
    		var reg = new RegExp("^[0-9]*$");
    		var reg2 = new RegExp("^\\d+(\\.\\d+)?$");  
    		if(!reg.test(strId)){
    			alert("图书编号输入不合法");
    			document.getElementById("id").value = "";
    			return false;
    		}
    		if(strId =="" ||strId == null){
    			alert("编号不能为空");
    			return false;
    		}
    		if(strName==""||strName ==null){
    			alert("书名不能为空");
    			return false;
    		}
    		if(strAuthor==""||strAuthor==null){
    			alert("作者不能为空");
    			return false;
    		}
    		if(strDescration==""||strDescration==null){
    			alert("描述不能为空");
    			return false;
    		}
    		if(!reg2.test(strOldPrice)){
    			alert("输入合法的原价");
    			return false;
    		}
    		if(!reg2.test(strNewPrice)){
    			alert("输入合法的现价");
    			return false;
    		}
    		if(!reg.test(strAmount)){
    			alert("输入合法的数量");
    			return false;
    		}
    		window.location.href="admin/GetAllBook.action";    		
    		return true;
    		
    	}
    </script>
	 
</html>
