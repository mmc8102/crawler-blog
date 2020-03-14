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
                    <img src="/img/download_icon.png"/>
                    本站源码下载
                </div>
                <div>
                    <p>
                        <br/>
                    </p>
                    <p>
                        本站源码使用J2EE开发；
                    </p>
                    <p>
                        <br/>
                    </p>
                    <p>
                        使用Spring4+Springmvc+Mybatis3架构，
                    </p>
                    <p>
                        采用Mysql数据库；
                    </p>
                    <p>
                        使用Maven3管理项目，使用Shiro作为项目安全框架，使用Lucene作为全文检索，支持restful风格；
                    </p>
                    <p>
                        前台网页使用主流的Bootstrap3 UI框架；后台管理使用主流易用的EasyUI轻量级框架；
                    </p>
                    <p>
                        数据库连接池使用的是阿里巴巴的Druid；
                    </p>
                    <p>
                        在线编辑器使用了百度的UEditor，支持单图，多图上传，支持截图上传，支持代码高亮特性；
                    </p>
                    <p>
                        <br/>
                    </p>
                    <p>
                        V1.0 2016年2月2号发布
                    </p>
                    <p>
                        高清功能演示视频以及完整源码和数据库脚本下载地址：<a href="http://pan.baidu.com/s/1sk23EUD" target="_blank" title="http://pan.baidu.com/s/1sk23EUD">http://pan.baidu.com/s/1sk23EUD</a>
                    </p>
                    <p>
                        <br/>
                    </p>
                    <p>
                        V2.0 2016年2月22号发布
                    </p>
                    <p>
                        完整源码和数据库脚本下载地址：<a href="http://pan.baidu.com/s/1eRfrsAm" target="_blank" title="http://pan.baidu.com/s/1eRfrsAm">http://pan.baidu.com/s/1eRfrsAm</a>
                    </p>
                    <p>
                        修复一些bug;<br/>
                        添加评论审核功能;<br/>
                        添加友情链接功能;<br/>
                        日志类别排序显示功能;<br/>
                        博客显示列表加博客缩略图功能;<br/>
                        博客加关键字功能;<br/>
                        博客加分享链接功能;<br/>
                    </p>

                    <p>
                        <br/>
                    </p>
                    <p>
                        V3.0 预计2016年4月1号正式发布；(目前内测中)
                    </p>
                    <p>
                        优化系统，提高访问速度;<br/>
                    </p>
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