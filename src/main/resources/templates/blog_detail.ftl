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

<style type="text/css">
	body {
		padding-top: 10px;
		padding-bottom: 40px;
	}
</style>
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?aa5c701f4f646931bf78b6f40b234ef5";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
<script type="text/javascript">
	<#--function loadimage(){
		document.getElementById("randImage").src="${pageContext.request.contextPath}/image.jsp?"+Math.random();
	}
	-->
	function submitData(){
		var content=$("#content").val();
		if(content==null || content==''){
			alert("请输入评论内容！");
		}else{
			$.post("/comment/save.do",{'content':content,'blog.id':'${blog.id}'},function(result){
				if(result.success){
					window.location.reload();
					alert("评论已提交成功，审核通过后显示！");
				}else{
					alert(result.msg);
				}
			},"json");
		}
	}

	function showOtherComment(){
		$('.otherComment').show();
	}
</script>
</head>
<body>
<div class="container">
	<#include "common/head.html" />
	<#include "common/menu.ftl" />

	<div class="row">
		<div class="col-md-9">
			<div class="data_list">
				<div class="data_list_title">
					<img src="/img/blog_show_icon.png"/>
					博客信息
				</div>
				<div>
					<div class="blog_title"><h3><strong>${blog.title }</strong></h3></div>
					<div class="blog_info">
						发布时间：『 ${(blog.updateTime)!}』&nbsp;&nbsp;博客类别：${(blog.blogType.typeName)!''}&nbsp;&nbsp;阅读(${blog.readCount}) 评论(${blog.replyCount})
					</div>
					<div class="blog_content">
						${blog.content}
					</div>
					<div class="blog_keyWord">
						<font><strong>关键字：</strong></font>
						<#if blog.keyWord??>
							&nbsp;&nbsp;${blog.keyWord}
						<#else>
							&nbsp;&nbsp;无
						</#if>
					</div>
					<div class="blog_lastAndNextPage">
						${pageCode}
					</div>
				</div>
			</div>
			<div class="data_list">
				<div class="data_list_title">
					<img src="/img/comment_icon.png"/>
					评论信息
					<#--<#if replys?size gt 10>
						<a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
					</#if>-->
				</div>
				<div class="commentDatas">
					<#--<#if replys?size==0>
						暂无评论
					<#else>
						<#list replys as r>
							<#if r_index<10>
								<div class="comment">
									<span><font>${r_index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${r.logininfo.username! }：</font>${r.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;${(r.replyTime?string("yyyy-MM-dd HH:mm:ss"))!}&nbsp;]</span>
								</div>
							<#else>
								<div class="otherComment">
									<div class="comment">
										<span><font>${r_index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${r.logininfo.username!}：</font>${r.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;${(r.replyTime?string("yyyy-MM-dd HH:mm:ss"))!}&nbsp;]</span>
									</div>
								</div>
							</#if>
						</#list>
					</#if>/#-->
				</div>
			</div>

			<div class="data_list" >
				<div class="data_list_title">
					<img src="/img/publish_comment_icon.png"/>
					发表评论
				</div>
				<div class="publish_comment">
					<div>
						<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
					</div>
					<div class="publishButton">
						<button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
					</div>
				</div>
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
</html>