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
		onClickRow:function(rowIndex, rowData){
			if(rowData.state){
				$("#emp_datagrid_edit,#emp_datagrid_del").linkbutton("enable");
			}else{
				//禁用按钮
				$("#emp_datagrid_edit,#emp_datagrid_del").linkbutton("disable");
			}
		}, 
		columns:[[
			{field:'id',title:'编号',align:'center',width:1},
			{field:'title',title:'标题',align:'center',width:2},
			{field:'url',title:'原始地址',align:'center',width:2,formatter:urlFormatter},
			{field:'status',title:'状态',align:'center',width:1,formatter:stateFormatter},
			{field:'createTime',title:'抓取日期',align:'center',width:1,formatter : function(value){
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}},
		]]
	});
	
	$("#emp_dialog").dialog({
		height:300,
		width:251,
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
	$("#emp_form").form("clear");
}
function edit(){
	//获取到选中的数据
	var rowData = $("#emp_datagrid").datagrid("getSelected");
	if(rowData){
		$("#emp_dialog").dialog("open");
		$("#emp_dialog").dialog("setTitle","编辑");
		$("#emp_form").form("clear");
		//特殊属性处理
		if(rowData.dept){
			rowData["dept.id"] = rowData.dept.id;
		}
		//发送一个同步请求到后台查询该员工角色集合
		 var html = $.ajax({
			  url: "/emp_queryByEid?id="+rowData.id,
			  async: false
			 }).responseText;
		 console.log(html);
		 html = $.parseJSON(html);
		 $("#emp_roles").combobox("setValues",html);
		$("#emp_form").form("load",rowData);//基于同名匹配规则
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function del(){
	//获取到选中的数据
	var rowData = $("#emp_datagrid").datagrid("getSelected");
	if(rowData){
        $.messager.confirm('温馨提示', '你确定离职该员工？', function(r){
            if (r){
            	//删除数据
            	$.get("/employee_delete?id="+rowData.id,function(data){
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
		url = '/employee_update';
	}else{
		url = '/employee_save';
	}
	$("#emp_form").form("submit",{
		url:url,
		onSubmit: function(param){ 
			var values = $("#emp_roles").combobox("getValues");
			for(i=0; i<values.length; i++){
				param["roles["+i+"].id"] = values[i];    
			}
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