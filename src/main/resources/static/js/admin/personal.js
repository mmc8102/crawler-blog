$(function() {
	$("#per_datagrid").datagrid({
		title : '个人信息',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : '#per_datagrid_bt',
		url : '/admin/user/info',
		columns : [ [ 
			{field : 'realName',title : '真实姓名',align : 'center',width : 1},
			{field : 'number',title : '工号',align : 'center',width : 1},
			{field : 'sex',title : '性别',align : 'center',width : 1,formatter:sexFormatter},
			{field : 'email',title : '邮箱',align : 'center',width : 1}
			] ]
	});

	$("#per_dialog").dialog({
		height : 200,
		width : 251,
		buttons : "#per_dialog_bt",
		closed : true
	});

	$("#per_dialog1").dialog({
		height : 220,
		width : 251,
		buttons : "#dialog_bt",
		closed : true
	});

	// 验证密码
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '两次密码输入不一致!'
		}
	});
	// 为输入框添加校验事件
	$('.easyui-validatebox').bind('blur', function() {
		$(this).validatebox('enableValidation').validatebox('validate');
	});
	
	/*$('#username').bind('blur', function () { 
		//新增时才去查询账号是否存在
		$.extend($.fn.validatebox.defaults.rules, {
		    myvalidate : {
		        validator : function(value, param) {
		            var username = $("#username").val().trim();
		            var haha = "";
		            $.ajax({
		                type : 'get',
		                async : false,
		                url : '/queryUserByUsername',
		                data : {
		                    "username" : username
		                },
		                success : function(data) {
		                    haha = data;
		                }
		            });
		            return haha;
		        },
		        message : '该用户名已存在!'
		    }
		});
	});*/
});

function sexFormatter(value,row,index){
	return value==0?"女":"男";
}

function edit() {
	//获取到选中的数据
	var rowData = $("#per_datagrid").datagrid("getSelected");
	console.log(rowData);
	if(rowData){
		$("#per_dialog1").dialog("open");
		$("#per_dialog1").dialog("setTitle","编辑");
		$("#per_form1").form("clear");
		$("#per_form1").form("load",rowData);//基于同名匹配规则
		//特殊属性处理
		if(rowData.sex){
			$("[name=sex]:first").prop("checked", true);
		}else{
			$("[name=sex]:last").prop("checked",true);
		}
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}

function editPWD() {
	$("#per_dialog").dialog("open");
	$("#per_dialog").dialog("setTitle", "修改密码");
	$("#per_form").form("clear");
}

function save() {
	$("#per_form1").form("submit",{
		url:'/admin/user/update',
		success:function(data){
			data = $.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					$("#per_dialog1").dialog("close");
					$("#per_datagrid").datagrid("load");
				});
			}else{
				$.messager.alert("温馨提示",data.msg,"info");
			}
		}
	});
}

function savePWD() {
	$("#per_form").form(
			"submit",
			{
				url : "/admin/user/update_pwd",
				onSubmit : function() {
					// 在表单提交前，做一下验证
					$('.easyui-validatebox').validatebox('enableValidation')
							.validatebox('validate');
					return $("#per_form").form("validate");
				},
				success : function(data) {
					data = $.parseJSON(data);
					if (data.success) {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							$("#per_dialog").dialog("close");
							window.location.href = "/admin";
						});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}
			});
}

function cancel() {
	$("#per_dialog,#per_dialog1").dialog("close");
}