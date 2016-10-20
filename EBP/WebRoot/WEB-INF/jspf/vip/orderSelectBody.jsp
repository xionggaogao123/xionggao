<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@page import="java.util.Random"%>
<%@ page import="java.security.*" %>
<%@ page import="com.sun.org.apache.xml.internal.security.utils.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript">
	function deleteOrder(id){
		if(confirm("确定要删除该订单？")){
			$.get("DeleteOrder.jsp?id="+id,function(msg){
					$("#"+id).css("display","none");
			});
		}	
	}
</script>
<div class="person-right2">
            <div class="person-top">
              <h4>我的订单</h4>
        	</div>

	        <div class="indent">
	          	<div class="panel  panel-warning">
	              	<div class="panel-heading">
	                  	<h3 class="panel-title">
	                     		订单详情
	                  	</h3>
	              	</div>
	              <c:choose>
		    	<c:when test="${empty orderList}">
		    		<h2>订单为空....<a href="index.jsp">回到首页继续购物</a></h2>
		    	</c:when>
		    	<c:otherwise>
	              <!-- 第一次遍历获取每一个订单 -->
	              <c:forEach items="${orderList}" var="order">
	              	<div class="panel-body" id="${order.id}">
		              	<div class="panel panel-default">
		                  	<div class="panel-heading">
		                      	<h3 class="panel-title">
		                         	<span class="date">${order.createTime}&nbsp;&nbsp;&nbsp;订单号:&nbsp;</span><span class="order-number">${order.orderNum}</span><span>&nbsp;&nbsp;&nbsp;总共花费：&nbsp;${order.price}</span>
		                         	<span class="glyphicon glyphicon-trash" onclick="deleteOrder('${order.id}')" style="float:right;"></span>
		                      	</h3>
			                  </div>
			                <!-- 遍历该订单下的每一个订单项 -->
			                <c:forEach items="${order.items}" var="item">
			                <div class="panel-body">
			                      <div class="col-md-1">
			                          <img src="${item.book.filename}" alt="" style="width: 64px;"/>
			                      </div>
			
			                      <div class="col-md-3">
			                          <span style="font-size: 16px;font-weight: bold;">${item.book.name}</span><br/>
			                          <span>${item.book.author}</span>
			                          <p style="color: gray; margin-top: 20px;">¥<span class='unit-price'>${item.book.newPrice}</span></p>
			                      </div>
			
			                      <div class="col-md-2">
			                      </div>
			
			                      <div class="col-md-1 b">
			                       	<p>x<span class="book-number">${item.amount}</span></p>
			                      </div>
			
			                      <div class="col-md-2 b">
			                        	<span>退/换货</span>
			                      </div>
			
			                      <div class="col-md-2 b" style="text-align: center;">
			                       <p style="border-bottom: 1px solid #DBDBDB;">总额:<span class='total-amount'>${item.price }</span></p>
			                       <span>在线支付</span>
			                      </div>
			
			                      <div class="col-md-1">
			                          
			                      </div>
			               	 </div>
			                </c:forEach>  
			                  
		                </div>
		           </div>
	              </c:forEach>
	              </c:otherwise>
		    	</c:choose>
		      	</div>
	 		</div>
          </div>
          
