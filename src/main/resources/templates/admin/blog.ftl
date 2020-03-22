<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理</title>
<#include "common.ftl"/>
<script type="text/javascript" src="/js/admin/blog.js"></script>
</head>
<body>
	<table id="emp_datagrid"></table>
	
	<!-- 新增/编辑对话框 -->
	<div id="emp_dialog">
		<form id="emp_form" method="post">
			<input type="hidden" name="id"/>
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>账号</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>真实姓名</td>
					<td><input type="text" name="realname"/></td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td><input type="text" name="tel"/></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input type="text" class="easyui-datebox" name="inputtime"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 工具栏按钮 -->
	<div id="emp_datagrid_bt">
		<div>
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
				<a id="emp_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
				<a id="emp_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">离职</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reflesh()">刷新</a>
		</div>
		<div>
			标题:<input type="text" name="keyWord"/><a class="easyui-linkbutton" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		</div>
	</div>
	<!-- 对话框底部按钮 -->
	<div id="emp_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>