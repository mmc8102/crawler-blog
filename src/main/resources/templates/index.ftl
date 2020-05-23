<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客采集系统</title>

<link rel="stylesheet" href="/js/bootstrap-3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/js/bootstrap-3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/blog.css">
<script src="/js/jquery-2.1.3.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-paginator.js"></script>

<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?aa5c701f4f646931bf78b6f40b234ef5";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
</script>

<style type="text/css">
	  body {
        padding-top: 10px;
        padding-bottom: 40px;
      }
</style>
</head>
<body>
<div class="container">
	<#include "common/head.html" />
	<#include "common/menu.ftl" />

	<div class="row">
		<div class="col-md-9">
			<div class="data_list">
				<div class="data_list_title">
					<img src="/img/list_icon.png"/>
					最新博客</div>
				<div class="datas">
					<ul>
						<#list blogs.rows as blog>
							<li style="margin-bottom: 30px">
								<a href="/blog/query_detail/${blog.id?c}">
									<span class="title">${blog.title}</span>
									<span class="summary">摘要: ${blog.summary!''}...</span>
									<span class="info">发表于 ${(blog.updateTime)!}  阅读(${blog.readCount}) 评论(${blog.replyCount}) </span>
								</a>
							</li>
							<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
						</#list>
					</ul>
				</div>
			</div>

			<div>
				<nav>
					<#--<ul class="pagination pagination-sm">
						${pageCode}
					</ul>-->
					<div id="example" style="text-align: center"> <ul id="pageLimit"></ul> </div>
				</nav>
			</div>
		</div>
		
		<div class="col-md-3">
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="/img/byType_icon.png"/>
					按博客类别
				</div>
					<div class="datas">
						<ul>
							<#list types as t>
								<li><span><a href="/index?typeId=${t.id}">${t.typeName}</a></span></li>
							</#list>
						</ul>
					</div>
			</div>

		</div>


	</div>
	<#include "common/foot.html" />
</div>
</body>
<script>
    $('#pageLimit').bootstrapPaginator({
        currentPage: "${qo.page!1}",//当前页。
        totalPages: "${blogs.totalPage?c}",//总页数。
        size:"normal",//应该是页眉的大小。
        bootstrapMajorVersion: 3,//bootstrap的版本要求。
        alignment:"right",
        numberOfPages:8,//显示的页数
        itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
        	switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
            /*$.ajax({
                url:'',
                type:'get',
                data:{},
                dataType:'JSON',
                success:function (callback) {

                }
            })*/
            var url = "/index?page="+page;
			var keyword = "${qo.keyWord!""}"
			var typeId = ${qo.typeId!0};
			console.log(keyword);
			console.log(typeId);
            if(keyword && keyword!=""){
            	url += "&keyWord="+keyword;
			}
            if(typeId>0){
				url += "&typeId="+typeId;
			}
			console.log(url);
            window.location.href=url;
        }
    });

</script>
</html>