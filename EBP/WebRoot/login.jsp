<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ page import = "com.wuxianedu.service.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<font color='red'><s:actionerror /></font>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">
     <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name = "viewport" content = " width = device-width, initial-scale = 1 "> 
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
		<style>
            *{
                margin: 0px;
                padding: 0px;
            }
            /*布局开始*/
            #login_dialog {
                position: fixed;
                left: 50%;
                top: 30%;
                /* background-color: #303a40; */
                width: 500px;
                margin-left: -200px;
                margin-top: -150px;
                font-family: "黑体";
                /*禁止复制粘贴*/
                -moz-user-select: none;
                -webkit-user-select: none;
                user-select:none;
            }
            .register_dialog_title {
                height: 35px;
                line-height: 35px;
                margin: 0 5px;
            }

            .register_dialog_info {
                float: left;
                margin-left:10px;
                color: #fff;
                margin-top: 5px;
                font-size: 20px;
            }
            #register_dialog {
                position: fixed;
                left: 40%;
                top: 40%;
                background-color: #303a40;
                width: 500px;
            /*  height: 600px; */
                margin-left: -200px;
                margin-top: -200px;
                font-family: "黑体";
                -moz-user-select:none; /*火狐*/
                -webkit-user-select:none; /*webkit浏览器*/
                -ms-user-select:none; /*IE10*/
                -khtml-user-select:none; /*早期浏览器*/
                user-select:none;
            }

            .register_dialog_info {
                float: left;
                margin-left:10px;
                color: #fff;
                margin-top: 5px;
                font-size: 20px;
            }
            .dialog_close {
                cursor: pointer;
                width: 40px;
                height:40px;
                border-radius:25px;
                float: right;
                line-height:40px;
                font-size: 20px;
                color: #d8dadb;
                font-family: "微软雅黑";
                text-align: center;
                cursor:center;
            }
            form{padding: 20px 0px;}
            ul li {list-style: none;}
            .sub {
                text-align: center;
            }
            .sub input {
                display: inline-block;
                width: 300px;
                background-color: rgb(255, 109, 11);
                color: rgb(255, 255, 255);
                font-size: 20px;
                text-align: center;
                height: 40px;
                line-height: 40px;
                font-family: 黑体;
                outline: none;
                border: none;
                margin: auto;
            }
            .dialog_close:hover {
                background-color: #566
            }
            input[type = "submit"]:hover{cursor: pointer;}
            /*布局结束*/
            .reg-box { padding-left: 30px; }

            .reg-box li { line-height: 40px; width: 500px; overflow: hidden; height:80px;}

            .reg-box li label { width: 70px; height: 60px; float: left; line-height: 60px; text-align: right; padding-right: 20px; }

            .reg-box li input,.reg-box li select{ padding: 6px 0; font-size: 16px; width: 296px; height: 50px; line-height: 28px; border: 1px solid #dddddd; text-indent: 0.5em; float: left; }

            .reg-box li select option{font-size:16px;}

            .registered .btn a { background: #ff7200; margin-left: 110px; }

            /*验证码*/
            .add { width: 128px; height: 44px; float: left; _display: inline; cursor: pointer; margin-left: 20px; }

            .reg-box li .sradd { width: 148px; text-indent: 4px; font-size: 14px; }

            .reg-box li .input-code { width: 106px; padding: 10px; font-family: Arial; font-style: italic; color: red; letter-spacing: 1px; cursor: pointer; text-align: center; text-indent: 0; }

            .yzm,.phoKey { background: white; text-align: center; line-height: 44px; color: #fff; }

            .phoKey{letter-spacing: 3px; font-size:16px;}

            .yzmc { background: #dddddd; text-align: center; line-height: 44px; color: #999; }

            .error { clear:both;display:block;color: red; padding-left: 90px; padding-bottom:5px;height:20px;float: left; font-size:12px;line-height: 20px;}

            input { background-color: #fff; outline: none; }

            .reg-box li { width: auto; }
            
            .reg-box li input.errorC, .errorC{ border: 1px solid red; }

            .reg-box li input.checkedN , .checkedN{ border: 1px solid #1ece6d; }

        </style>
        
  </head>
  
  <body>
    	 <div id="login_dialog" class="panel panel-success">
             <div class="panel-heading"> 
                <h3 class="panel-title">登录页面</h3> 
             
            </div> 
            
            <div style="background-color:#ffffff;margin:10px;" class="panel-body">
                <form action='<c:url value="user/Login"/>' method='POST'>                
                    <ul class="reg-box">                 
                        <li>
                            <label for="">账    号</label><input type="text" name="username" value="${param.username}" placeholder="请输入您的账号" onBlur="textBlur(this)" onFocus="textFocus(this)" class="account" maxlength="20" style="color:#999;" /><br>
                            <p><font color='red' size="-1"><i><s:fielderror> <s:param>username</s:param> </s:fielderror> </i></font> </p>
                        </li>
                        <li>
                            <label for="">密    码</label><input type="password" name="password" value="${param.password}" class = "admin_pwd" placeholder="请输入密码" onBlur="textBlur(this)" onFocus="textFocus(this)" style="color:#999;"/><br>
                             <p>
	                            <font color='red' size="-1"><i>
				                    <s:fielderror>
				                        <s:param>password</s:param>
				                    </s:fielderror>
				                </i></font>
			                </p>
                        </li>
                        <li>
                            <label for="">验证码</label><input type="text" name="verifyCode" class="sradd photokey" placeholder="请输入验证码" style="color:#999;" onBlur="textBlur(this)" onFocus="textFocus(this)" /><span class="add phoKey"><img src="servlet/RandomImageServlet.view" onclick="this.src='servlet/RandomImageServlet.view?'+Math.random()"></span><br>
                       		<p>
	                            <font color='red' size="-1"><i>
				                    <s:fielderror>
				                        <s:param>verifyCode</s:param>
				                    </s:fielderror>
				                </i></font>
			                </p>
                        </li>
                    </ul>
                    <div class="sub">
                        <input type="submit" class="loin_btn" value="立即登录" /><h4 style="float:right;"><a href="register.jsp">立即注册</a></h4>
                    </div>                  
                </form>
            </div>
            
        </div>
  </body>
</html>

