<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理</title>
<#include "common.ftl"/>
<script type="text/javascript" src="/js/admin/blog.js"></script>
<style>
	.model{
		height: 50px;
		width: 800px;
		font-size: 16px;
	}
	.td{
	}

</style>
</head>
<body>
	<table id="emp_datagrid"></table>

	<!-- 工具栏按钮 -->
	<div id="emp_datagrid_bt">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			<a id="emp_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			<a id="emp_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reflesh()">刷新</a>
		</div>
		<div>
			标题:<input type="text" name="keyWord"/><a class="easyui-linkbutton" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		</div>
	</div>

	<!-- 新增/编辑对话框 -->
	<div id="emp_dialog">
		<form id="emp_form" method="post">
			<input type="hidden" name="id"/>
			<table align="center" style="margin: 20px 50px;">
				<tr class="model">
					<td>博客标题:</td>
					<td class="td">
						<input class="easyui-validatebox" type="text" name="title" data-options="required:true,novalidate:true" style="width: 600px; height: 20px;"/>
					</td>
				</tr>
				<tr class="model">
					<td>帖子类别:</td>
					<td class="td" style="width: 150px;">
						<input type="text" class="easyui-combobox" name="typeId" data-options="valueField:'id',textField:'typeName',url:'/blog_type/list'"/>
					</td>
				</tr>
				<tr class="model">
					<td>帖子状态:</td>
					<td class="td">
						<select name="status" class="easyui-combobox" style="width: 172px;">
							<#if status??>
								<option value="0" selected>未发布</option>
								<option value="1">已发布</option>
							<#else>
								<option value="0">未发布</option>
								<option value="1" selected>已发布</option>
							</#if>
						</select>
					</td>
				</tr>
				<tr class="model">
					<td>帖子内容:</td>
					<td class="td" id="content1">

					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 对话框底部按钮 -->
	<div id="emp_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>