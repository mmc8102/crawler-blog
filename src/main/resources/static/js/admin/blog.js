$(function(){
	$("#emp_datagrid").datagrid({
		title:'博客信息',
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		toolbar:'#emp_datagrid_bt',
		pageList:[10,20,30,40,50],
		url:'/admin/blog/list',
		columns:[[
			{field:'id',title:'编号',align:'center',width:1},
			{field:'title',title:'标题',align:'center',width:3},
			{field:'url',title:'原始地址',align:'center',width:2,formatter:urlFormatter},
			{field:'status',title:'状态',align:'center',width:1,formatter:stateFormatter},
			{field:'createTime',title:'抓取日期',align:'center',width:1,formatter : function(value){
					return new Date(value).toLocaleString();
			}},
		]]
	});
	
	$("#emp_dialog").dialog({
		height:680,
		width:1000,
		buttons:"#emp_dialog_bt",
		closed:true,
	});
});

function urlFormatter(value,row,index){
	return value?"<a href= "+value+">"+value+"</a>":null;
}
function stateFormatter(value,row,index){
	return value==1?"<font style='color:green'>已发布</font>":"<font style='color:red'>未发布</font>";
}

function add(){
	$("#emp_dialog").dialog("open");
	$("#emp_dialog").dialog("setTitle","新增");
	$(".easyui-validatebox").validatebox({novalidate:true});
	$("#emp_form").form("clear");
}
function edit(){
	//获取到选中的数据
	var rowData = $("#emp_datagrid").datagrid("getSelected");
	$(".easyui-validatebox").validatebox({novalidate:true});
	if(rowData){
		$("#emp_dialog").dialog("open");
		$("#emp_dialog").dialog("setTitle","编辑");
		$("#emp_form").form("clear");
		/*//特殊属性处理
		if(rowData.dept){
			rowData["dept.id"] = rowData.dept.id;
		}*/
		//发送一个同步请求到后台查询该员工角色集合
		 /*var html = $.ajax({
			  url: "/admin/blog/get?id="+rowData.id,
			  async: false
			 }).responseText;
		 html = $.parseJSON(html);*/
		 $("#content").html(rowData.content);
		$("#emp_form").form("load",rowData);//基于同名匹配规则
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function del(){
	//获取到选中的数据
	var rowData = $("#emp_datagrid").datagrid("getSelected");
	if(rowData){
        $.messager.confirm('温馨提示', '你确定删除该博客吗？', function(r){
            if (r){
            	//删除数据
            	$.get("/admin/blog/delete?id="+rowData.id,function(data){
            		if(data.success){
            			$.messager.alert("温馨提示",data.msg,"info");
            			$("#emp_datagrid").datagrid("reload");
            		}else{
            			$.messager.alert("温馨提示",data.msg,"info");
            		}
            	},'json');
            }
        });
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function reflesh(){
	$("#emp_datagrid").datagrid("load");
}
function save(){
	var idVal = $("#emp_form [name=id]").val();
	var url;
	if(idVal){
		url = '/admin/blog/update';
	}else{
		url = '/admin/blog/save';
	}
	$("#emp_form").form("submit",{
		url:url,
		onSubmit: function(param){
			// 在表单提交前，做一下验证
			$('.easyui-validatebox').validatebox('enableValidation').validatebox('validate');
	    },
		success:function(data){
			data = $.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					$("#emp_dialog").dialog("close");
					$("#emp_datagrid").datagrid("load");
				});
			}else{
				$.messager.alert("温馨提示",data.msg,"info");
			}
		}
	});
}

function cancel(){
	$("#emp_dialog").dialog("close");
}

function searchBtn(){
	var value = $("[name='keyWord']").val();
	$("#emp_datagrid").datagrid("load",{
		keyWord:value
	});
}