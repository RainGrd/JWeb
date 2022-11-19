<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/23
  Time: 15:27:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--加入该标签手动开启el功能--%>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新闻管理系统</title>
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/JQuery.min.jS"></script>
    <script src="assets/layui/layui.js" charset="utf-8"></script>
    <script src="assets/js/jquery.cookie.js"></script>
    <script src="assets/js/axios.js"></script>
    <script src="assets/layui/jquery.particleground.min.js" charset="utf-8"></script>
    <link
            rel="shortcut icon"
            type="images-icon"
            href="images/icon/doctor.ico"
    />
<%--    <style>
        body{
            margin: 0;
            padding: 0;
            overflow-y: hidden;
        }
    </style>
    <script>
        window.onerror=function () {
            return true;
        }
        $(function () {
            header=H;
            var h=$(window).height();
            $('#iframe').height((h-headerH)+"px")
        })
    </script>--%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">学生管理系统</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 移动端显示 -->
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
                <i class="layui-icon layui-icon-spread-left"></i>
            </li>

            <li class="layui-nav-item layui-hide-xs"><a href="">控制台</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="">nav 2</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="">nav 3</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">nav groups</a>
                <dl class="layui-nav-child">
                    <dd><a href="">menu 11</a></dd>
                    <dd><a href="">menu 22</a></dd>
                    <dd><a href="">menu 33</a></dd>
                </dl>
            </li>


        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <!--头像-->
                <a href="javascript:;">
                    <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                         class="layui-nav-img">
                    admin
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">修改密码</a></dd>
                    <dd><a href="login.jsp">退出登录</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-more-vertical"></i>
                </a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">主要功能</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">学生列表</a></dd>
                        <dd><a href="/Management-System/allteacher.jsp">教师列表</a></dd>
                        <dd><a href="javascript:;">添加学生</a></dd>
                        <dd><a href="javascript:;">添加教师</a></dd>
                        <%--<dd><a href="javascript:;">2107</a></dd>--%>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">教师</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">list 1</a></dd>
                        <dd><a href="javascript:;">list 2</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">click menu item</a></li>
                <li class="layui-nav-item"><a href="">the links</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe src="" id="main" height="100%" width="100%" >

        </iframe>
        <%--<div style="padding: 15px;">
            内容主体区域

            <br><br>

            <blockquote class="layui-elem-quote layui-text">
                <ul>
                    <li>
                        你也可以单独打开管理界面大布局的演示页面：
                        <a class="layui-btn layui-btn-normal" href="layuiAdmin.html" target="_blank">单独打开</a>
                    </li>
                    <li>
                        该页面只是简单的管理系统的界面基础布局示例，并不是一整套界面布局方案，您可以关注基于 layui 的通用型管理系统界面模板解决方案：
                        <a href="/layuiadmin/" target="_blank" class="layui-btn">layuiAdmin</a>
                    </li>
                    <li>
                        layui 之所以赢得如此多人的青睐，更多是在于它“前后界面兼备”的能力。既可编织出绚丽的前台页面，又可满足繁杂的管理系统的界面需求。
                        <br>layui 管理基本布局， 致力于让每一位开发者都能轻松搭建自己的管理系统模板。
                    </li>
                </ul>
            </blockquote>

            <a href="/doc/element/layout.html#admin" target="_blank" class="layui-btn">查看该布局代码</a>
            <br><br><br>

            <div class="layui-card layui-panel">
                <div class="layui-card-header">
                    下面是充数内容，为的是出现滚动条
                </div>
                <div class="layui-card-body">
                    充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>你还真滑到了底部呀
                </div>
            </div>
            <br><br>


        </div>--%>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        底部固定区域
    </div>
</div>
<div id="rightPanel" style="display: none;padding: 15px;">处理右侧面板的操作</div>
<%--<div class="layui-body">

</div>--%>
<script>
    //JS
    layui.use(['element', 'layer', 'util'], function () {
        var element = layui.element
            , layer = layui.layer
            , util = layui.util
            , $ = layui.$;
        //头部事件
        util.event('lay-header-event', {
            //左侧菜单事件
            menuLeft: function (othis) {
                layer.msg('展开左侧菜单的操作', {icon: 0});
            }
            , menuRight: function () {
                layer.open({
                    type: 1
                    , content: $("#rightPanel")
                    , area: ['260px', '100%']
                    , offset: 'rt' //右上角
                    , anim: 5
                    , shadeClose: true
                    , flex: false,
                    resize: false,
                    move: false,
                });
            }
        });
        /*打开内容主体区域*/
        $(function (){
            $('dd>a').on('click',function (e) {
                e.preventDefault();
                $("#main").attr('src',$(this).attr('href'));
            })
        })
    });
</script>
</body>
</html>
