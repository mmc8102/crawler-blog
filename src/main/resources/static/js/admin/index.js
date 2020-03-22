$(function(){
	$("#menu").tree({
		url:'/admin/queryForMenu',
		onClick:function(node){
			if(node.attributes){
				node.attributes = $.parseJSON(node.attributes);
				//新增一个选项卡
				//判断面板中有没有该选项卡
				if($("#main_tabs").tabs("exists",node.text)){
					//如果有,选中即可
					$("#main_tabs").tabs("select",node.text);
				}else{
					//如果没有,新增
					$("#main_tabs").tabs("add",{
						title:node.text,
						closable:true,
						//只能加载body部分内容
						//href:node.attributes.url
						content:'<iframe src="'+node.attributes.url+'" style="width:100%;height:99%" frameborder=0></iframe>'
					});
				}
			}
		}
	});
	
	$("#main_tabs").tabs({
		fit:true
	});
	
});

