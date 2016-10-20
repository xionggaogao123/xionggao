<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@page import="java.util.Random"%>
<%@ page import="java.security.*" %>
<%@ page import="com.sun.org.apache.xml.internal.security.utils.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<base href="<%=basePath%>">
	
	<title>多看阅读个人中心</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<link rel="icon" href="image/icon.png">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/duokan.css">
	<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<style type="text/css">
	     *{margin: 0;padding: 0;}
	    /*---------- 左边导航 ----------*/
         .person-center{width: 1190px; margin: 0 auto;}
         .person-left{
         	width: 235px;
         	height: 500px;
         	float: left;
         }

         .person-left h3{color: gray;}
         .person-left a{font-size: 16px; font-weight: bold;}
         .person-left a:link{color: black;}
         .person-left a:visited{color: black;}
         .person-left a:active{color: black;}

         .act{
         	background-color: rgb(244,242,239);
         	border-right: 5px solid orange;
         }
        /* --------右边内容--------- */
        .person-right1,.person-right2,.person-right3{
          width: 955px;
          float: right;
          border-left: 1px solid #DBDBDB;
        }
        .person-top{
            width: 925px;
            margin-left: 30px;
            margin-top: 30px;
            padding-top: 10px;
            border-bottom: 1px solid #DBDBDB;
        }
        .person-content,.person-pay{
        	padding-top: 30px;
        	padding-left: 30px;
        }
        .error{
        	font-size: 16px;
        	color: red;
        }
        .person-right2 .indent{
          margin-top:10px;
          margin-left: 30px;
          width: 925px;
        }
        .person-right2 .indent p span{font-size: 14px;}
        .person-right2 .indent .date{color: gray;}
        /* 页面切换按钮 */
        .person-right2 .person-bottom{
          width: 955px;
        }
        .person-right2 .person-bottom button,.bottom-txt{
          float: right;
          margin-left: 10px;
        }
        .person-right2 .person-bottom .bottom-txt{
          /* width: 50px;
          height: 30px; */
          line-height: 35px;
          /* text-align: center; */
        }


        .person-price{
          padding-top: 10px;
          margin-left: 30px;
          border-bottom: 1px solid #DBDBDB;
        }
		.person-right3 .person-price .balance{
          font-size: 30px;
          font-weight: bold;
          color: rgb(229,158,80);
        }
        .person-right3 .person-price .pay{
          margin-top: 50px;
        }
        
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
	<div class="person-center">

   	  <!-- 头部分 -->
      <div class="headbox">
        <%@include file="/WEB-INF/jspf/common/header.jsp" %>
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
          <%@include file="/WEB-INF/jspf/common/mainnavigation.jsp" %>    
        </div>
	     <!-- 左边导航 -->
		 <div class="person-left">
		 	<%@include file="/WEB-INF/jspf/common/leftnavigation.jsp" %>
		 </div>
		 <!-- 主体内容部分 -->
	 	 <jsp:include page="/WEB-INF/jspf/${mainBody}" />
	</div>
	</div>
	
</body>
<script type="text/javascript">
	
	$('.person-left a').click(function  () {
	    $(this).parent().addClass('act');
	    $(this).parent().siblings().removeClass('act');
	});
	var i = 4; 
	function shownum(){ 
		i=i-1; 
		document.getElementById("time").innerHTML=i + "秒后";
		setTimeout('shownum()',1000); 
	} 
</script>
</html>