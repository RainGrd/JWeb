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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/assets/js/JQuery.min.jS"></script>
    <script src="${pageContext.request.contextPath}/assets/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/axios.js"></script>
    <script src="${pageContext.request.contextPath}/assets/layui/jquery.particleground.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/assets/js/laydate/laydate.js"></script>
    <link
            rel="shortcut icon"
            type="images-icon"
            href="${pageContext.request.contextPath}/images/icon/doctor.ico"
    />
    <style>
        b /*ody{
                margin: 0;
                padding: 0;
                overflow-y: hidden;
            }*/
        .layui-form-item, .layui-input-block {
            margin-left: 150px;
        }

        .layui-form-label {
            width: 90px;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">
            <img src="${pageContext.request.contextPath}/images/doctor.png" alt="logo" width="40">
            新闻管理系统
        </div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 移动端显示 -->
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
                <i class="layui-icon layui-icon-spread-left"></i>
            </li>

            <%--<li class="layui-nav-item layui-hide-xs"><a href="">控制台</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="">热点要闻</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="">nav 3</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">主题</a>
                <dl class="layui-nav-child">
                    <dd><a href="">军事</a></dd>
                    <dd><a href="">娱乐</a></dd>
                    <dd><a href="">menu 33</a></dd>
                </dl>
            </li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <!--头像-->
                <a href="javascript:;">
                    <c:if test="${sessionScope.user.userStatus==2}">
                        <img src="${pageContext.request.contextPath}/images/doctor.png"
                             class="layui-nav-img">
                    </c:if>
                    <c:if test="${sessionScope.user.userStatus==1}">
                        <img src="${pageContext.request.contextPath}/images/bg.jpg"
                             class="layui-nav-img">
                    </c:if>
                    <c:if test="${sessionScope.user.userStatus==0}">
                        <img src="${pageContext.request.contextPath}/images/logo.png"
                             class="layui-nav-img">
                    </c:if>
                    <%--用户名--%>
                    ${sessionScope.user.username}
                </a>
                <dl class="layui-nav-child">
                    <%--<dd><a href="">基本资料</a></dd>--%>
                    <dd><a href="#" class="change_password">修改密码</a></dd>
                    <dd><a href="#" class="exitLogin">退出登录</a></dd>
                </dl>
            </li>

            <%--<li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-more-vertical"></i>
                </a>
            </li>--%>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <%--<li class="layui-nav-item">
                    <a class="" href="javascript:;">常用功能区</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">查看新闻</a></dd>
                        <dd><a href="">发布新闻</a></dd>
                        <dd><a href="javascript:;">修改新闻</a></dd>
                        <dd><a href="javascript:;">删除新闻</a></dd>
                        &lt;%&ndash;<dd><a href="javascript:;">2107</a></dd>&ndash;%&gt;
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">复杂功能区</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">审批新闻</a></dd>
                        <dd><a href="javascript:;">管理主题</a></dd>
                        <dd><a href="">管理用户</a></dd>
                    </dl>
                </li>--%>
                <li class="layui-nav-item"><a href="/Press/allPress.jsp" class="adminPress" name="adminPress">管理新闻</a>
                </li>
                <li class="layui-nav-item"><a href="/Press/addPress.jsp" name="insertPress">发布新闻</a></li>
                <%--<c:if test="${sessionScope.user.userStatus!=2}">
                    <li class="layui-nav-item"><a href="javascript:;">管理主题</a></li>
                    <li class="layui-nav-item"><a href="javascript:;">管理用户</a></li>
                </c:if>--%>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <iframe src="" id="main" height="100%" width="100%" marginwidth="0" marginheight="0">

        </iframe>
    </div>
    <%--<div class="layui-footer">
        <!-- 底部固定区域 -->
        底部固定区域
    </div>--%>
</div>
<div id="rightPanel" style="display: none;padding: 15px;">处理右侧面板的操作</div>
<%--修改密码框--%>
<div id="changPassword" style="display: none; padding: 20px;">
    <form class="layui-form" method="post" id="changePassword">
        <div class="layui-form-item">
            <label class="layui-form-label">请输入新密码</label>
            <div class="layui-input-block">
                <input type="password" required lay-verify="required" id="newPwd" name="newPwd" placeholder="请输入你的新密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">再次输入密码</label>
            <div class="layui-input-block">
                <input type="password" required lay-verify="required" id="rNewPwd" name="rNewPwd" placeholder="请再输入新密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div></div>
        <%--请输入你的邮箱--%>
        <%--<div class="layui-form-item">
            <label class="layui-form-label"></label>
            <div class="layui-input-block">
                <select name="interest" lay-filter="aihao">
                    <option value="0">写作</option>
                    <option value="1">阅读</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">复选框</label>
            <div class="layui-input-block">
                <input type="checkbox" name="like[write]" title="写作">
                <input type="checkbox" name="like[read]" title="阅读">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开关关</label>
            <div class="layui-input-block">
                <input type="checkbox" lay-skin="switch">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开关开</label>
            <div class="layui-input-block">
                <input type="checkbox" checked lay-skin="switch">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="0" title="男">
                <input type="radio" name="sex" value="1" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">请填写描述</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>--%>
        <div class="layui-form-item" style="text-align:center;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="changePassword">立即提交</button>
                <button type="button" class="layui-btn layui-btn-normal">取消修改</button>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
</body>
</html>