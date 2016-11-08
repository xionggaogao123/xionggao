<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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
			<li class="rt-li">${user.username}</li>
			<li class="part">|</li>
			<li class="rt-li exit"><a href="user/Exit.action">注销</a></li>
			<li class="part">|</li>
			<li class="rt-li shop"><a href="shoppingcart.jsp">购物车</a></li>
			<li class="part">|</li>
			<li class="rt-li myPerson"><a href="vip/personalCenter.jsp">个人中心</a></li>
		</ul>
	</div>
</div>