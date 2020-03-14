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
		var imageCode=$("#imageCode").val();
		if(content==null || content==''){
			alert("请输入评论内容！");
		}else if(imageCode==null || imageCode==''){
			alert("请填写验证码！");
		}else{
			$.post("/comment/save.do",{'content':content,'imageCode':imageCode,'blog.id':'${blog.id}'},function(result){
				if(result.success){
					window.location.reload();
					alert("评论已提交成功，审核通过后显示！");
				}else{
					alert(result.errorInfo);
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
					<div style="padding-left: 330px;padding-bottom: 20px;padding-top: 10px">
						<div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
					</div>
					<div class="blog_info">
						发布时间：『 ${(blog.releaseTime?string("yyyy-MM-dd"))!}』&nbsp;&nbsp;博客类别：${(blog.blogType.typeName)!''}&nbsp;&nbsp;阅读(${blog.readCount}) 评论(${blog.replyCount})
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

				</div>
				<div class="commentDatas">

				</div>
			</div>

			<div class="data_list" >
				<div class="data_list_title">
					<img src="/img/publish_comment_icon.png"/>
					发表评论
				</div>
				<div class="publish_comment">

				</div>
			</div>
		</div>

		<div class="col-md-3">
			<div class="data_list">
				<div class="data_list_title">
					<img src="/img/byType_icon.png"/>
					按日志类别
				</div>
				<div class="datas">
					<ul>
						<#list types as t>
							<li><span><a href="">${t.typeName}</a></span></li>
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