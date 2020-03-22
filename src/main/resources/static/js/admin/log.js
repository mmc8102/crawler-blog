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
			{field:'opuser',title:'操作用户',align:'center',width:1,formatter:opuserFormatter},
			{field:'optime',title:'操作时间',align:'center',width:1},
			{field:'opip',title:'登陆IP',align:'center',width:1},
			{field:'function',title:'使用功能',align:'center',width:2},
			{field:'params',title:'操作参数信息',align:'center',width:2}
		]]
	});
});

function opuserFormatter(value,row,index){
	return value?value.username:value;
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