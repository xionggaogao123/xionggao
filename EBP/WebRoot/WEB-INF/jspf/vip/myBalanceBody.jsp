<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.security.*" %>
<%@ page import="com.sun.org.apache.xml.internal.security.utils.Base64" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="person-right3">
          <div class="person-top">
          <h4>我的余额</h4>
         </div>
         <div class="person-price">
                  <p class="balance"><span>${user.balance}</span>&nbsp;书币</p>
                  <h5><font color="red">&nbsp;${notEnoughMoney}</font></h5>
                  <h4 class="pay">账户充值</h4>
         </div>

         <div class="person-pay">
            <form class="form-horizontal" action="book/UpdateBalance.action" method="post">

              <div class="form-group">
                  <label for="inputPassword" class="col-md-2 control-label">账户:</label>
                  <div class="col-md-4">
                      <input type="text" class="form-control" id="inputPassword" name="username" value="${user.username }" readonly="readonly">
                  </div>
              </div>

               <div class="form-group">
                  <label for="inputPassword" class="col-md-2 control-label">充值金额:</label>
                  <div class="col-md-4">
                      <input type="text" class="form-control" id="inputPassword" name="balance" placeholder="请输入充值金额:">
                      <p><font size="3px" color="red">
	                      <s:fielderror>
	                      	<s:param>balance</s:param>
	                      </s:fielderror>
                      </font></p>
                  </div>
              </div>

                <div class="form-group">
                  <div class="col-md-offset-2 col-md-2">
                      <button type="submit" class="btn btn-primary">确认充值</button>
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