<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理系统</title>
	<#include "common.ftl"/>
	<link rel="stylesheet" href="/css/reset.css">
	<link rel="stylesheet" href="/css/public.css">
	<script type="text/javascript"src="/js/admin/index.js"></script>
</head>
<body>
	 <div class="public-header-warrp">
		<div class="public-header">
			<div class="content">
				<img  style="width: 300px;height: 60px;" src="/img/logo2.png"/>
				<div class="public-header-admin fr">
					<p class="admin-name"><font  color ="green">${logininfo.username}您好！</font> </p>
					<div class="public-header-fun fr">
						<a href="/admin/logout" class="public-header-loginout">安全退出</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north'" style="height:70px; "></div>   
	    <div data-options="region:'west',split:true" title="导航菜单" style="width:200px;">
	    	<!-- 菜单 -->
	    	<ul id="menu"></ul>
	    </div>  
	    <div data-options="region:'center'">
	    	<div id="main_tabs">
	    		<div title="欢迎页" closable="true">
	    			<h1>欢迎登陆系统</h1>
	    		</div>
	    	</div>
	    </div>   
	    <div data-options="region:'south'" style="height:30px;background: url('/img/banner-pic.gif') no-repeat;background-size: cover;">
	    	<center style="margin-top: 8px;">版权所有</center>
	    </div>   
	</div>
</body>
</html>