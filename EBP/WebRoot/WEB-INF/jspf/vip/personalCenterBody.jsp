<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.security.NoSuchAlgorithmException"%>
<%@ page import="java.security.MessageDigest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="person-right1">
	<div class="person-top">
		<h4>帐号设置</h4>
	</div>
	<div class="person-content">
		<form class="form-horizontal" action='<c:url value="book/UpdateUser.action" />' method="post">
			<div class="form-group">
				<label for="firstname" class="col-md-2 control-label">帐号:</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="firstname" value="${user.username }" readonly="readonly" name="username">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-md-2 control-label">新密码:</label>
				<div class="col-md-4">
					<input type="password" class="form-control" id="lastname" placeholder="请输入密码" name="password" value="${param.password }">
				</div>
				<p class="error">
					<s:fielderror>
		            	<s:param>password</s:param>
		            </s:fielderror>
		        </p>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-md-2 control-label">确认密码:</label>
				<div class="col-md-4">
					<input type="password" class="form-control" id="lastname" placeholder="请再次输入密码" name="repassword" value="${param.repassword }">
				</div>
				<p class="error">
					<s:fielderror>
		            	<s:param>repassword</s:param>
		            </s:fielderror>
		        </p>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-md-2 control-label">姓名:</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="lastname" placeholder="请输入姓名" name="name" value="${empty param ? user.name : param.name }">
				</div>
				<p class="error">
					<s:fielderror>
		            	<s:param>name</s:param>
		            </s:fielderror>
		        </p>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-md-2 control-label">电话:</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="lastname" placeholder="请输入新的手机号码" name="phone" value="${empty param ? user.phone : param.phone}">
					<p class="error">
						<s:fielderror>
			            	<s:param>phone</s:param>
			            </s:fielderror>
			        </p>
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-md-2 control-label">性别:</label>
				<div class="col-md-4">
					男：<input type="radio" id="lastname" name="gender" value="男" ${empty param ? user.gender eq '男' ? 'checked' : '' : param.gender eq '男' ? 'checked' : ''}>
					女：<input type="radio" id="lastname" name="gender" value="女" ${empty param ? user.gender eq '女' ? 'checked' : '' : param.gender eq '女' ? 'checked' : ''}>
				</div>
			</div>
		
			<div class="form-group">
				<div class="col-md-offset-2 col-md-4">
					<button type="submit" class="btn btn-primary">更新资料</button>
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
			<input type="hidden" name="hidden" value=<%=token %>>
		</form>
	</div>
</div>