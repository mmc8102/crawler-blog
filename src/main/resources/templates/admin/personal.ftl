<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理</title>
<#include "common.ftl"/>
<script type="text/javascript" src="/js/admin/personal.js"></script>
</head>
<body>
	<table id="per_datagrid"></table>
	
	<!-- 修改密码 -->
	<div id="per_dialog">
		<form id="per_form" method="post" >
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>原密码</td>
					<td><input type="password" name="password" class="easyui-validatebox" data-options="required:true,novalidate:true"/></td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input id="pwd" type="password" name="newPwd" class="easyui-validatebox" data-options="required:true,validType:'length[3,18]',novalidate:true" invalidMessage="有效长度3-18" validateOnBlur="true"/></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" name="password2" class="easyui-validatebox"  data-options="required:true,novalidate:true" validType="equals['#pwd']" /></td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<!-- 编辑个人信息 -->
	<div id="per_dialog1">
		<form id="per_form1" method="post" >
			<input type="hidden" name="id"/>
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>真实姓名</td>
					<td><input type="text" name="realName" class="easyui-validatebox" required="true" missingMessage="不能为空"/></td>
				</tr>
				<tr>
					<td>工号</td>
					<td><input type="text" name="number"/></td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
						<input type="radio" name="sex" value="1"/>男
						<input type="radio" name="sex" value="0"/>女
					</td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input type="email" name="email" class="easyui-validatebox" validtype="email" required="true" missingMessage="不能为空" invalidMessage="邮箱格式不正确" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<!-- 工具栏按钮 -->
	<div id="per_datagrid_bt">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">修改个人信息</a>
			<a id="per_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="editPWD()">修改密码</a>
		</div>
	</div>
	<!-- 对话框底部按钮 -->
	<div id="per_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="savePWD()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
	<div id="dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>