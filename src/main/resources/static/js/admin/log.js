$(function(){
	$("#log_datagrid").datagrid({
		title:'日志信息',
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		toolbar:'#log_datagrid_bt',
		pageList:[5,10,20,30,40,50],
		url:'/admin/log/list',
		columns:[[
			{field:'username',title:'操作用户',align:'center',width:1},
			{field:'loginTime',title:'登陆时间',align:'center',width:1},
			{field:'ip',title:'登陆IP',align:'center',width:1},
			{field:'status',title:'登陆状态',align:'center',width:1,formatter:stateFormatter},
			{field:'userType',title:'用户类型',align:'center',width:1,formatter:userTypeFormatter}
		]]
	});
});

function userTypeFormatter(value,row,index){
	return value==0?"<font style='color:red'>后台用户</font>":"<font style='color:green'>前台用户</font>";
}
function stateFormatter(value,row,index){
	return value==1?"<font style='color:green'>登陆成功</font>":"<font style='color:red'>登录失败</font>";
}

function searchBtn(){
	var value = $("[name=keyWord]").val();
	$("#log_datagrid").datagrid("load",{
		keyWord:value
	});
}

function reload(){
	$("#log_datagrid").datagrid("load");
}