<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/5/16
  Time: 16:24:23
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
    <title>新闻列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/JQuery.min.jS"></script>
    <script src="assets/layui/layui.js" charset="utf-8"></script>
    <script src="assets/js/jquery.cookie.js"></script>
    <script src="assets/js/axios.js"></script>
    <script src="assets/layui/jquery.particleground.min.js" charset="utf-8"></script>
    <script src="assets/js/laydate/laydate.js"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<style>
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
        top: 5px;
        padding: 0;
    }
    #pressDetails>p{
        text-align: justify;
        text-indent: 2em;
    }
</style>
<div class="layuimini-container">
    <!-- 顶部行内表单 -->
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px">
                <form class="layui-form layui-col-md12 layui-form-pane we-search" id="search">
                    <div class="layui-form-item">
                        <label class="layui-form-label">创建人：</label>
                        <div class="layui-input-inline">
                            <input type="text" autocomplete="off" name="pressPerson" id="pressPerson" placeholder=""
                                   autocomplete="off"
                                   class="layui-input">
                        </div>
                        <label class="layui-form-label">发表时间：</label>
                        <div class="layui-input-inline">
                            <input type="text" autocomplete="off" name="pressDate" id="pressDate" class="layui-input">
                        </div>
                    </div>
                    <c:if test="${sessionScope.user.userStatus==1}">
                        <label class="layui-form-label">新闻状态：</label>
                        <div class="layui-input-inline">
                            <select name="pressStatus" id="pressStatus" lay-search>
                                <option></option>
                                <option value="2">通过审批的新闻</option>
                                <option value="1">未审批的新闻</option>
                                <option value="3">被退回的新闻</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <label class="layui-form-label">置顶：</label>
                            <div class="layui-input-inline">
                                <select name="pressTop" id="pressTop" lay-search>
                                    <option value=""></option>
                                    <option value="1">置顶</option>
                                    <option value="2">未置顶</option>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.user.userStatus==0}">
                        <label class="layui-form-label">新闻状态：</label>
                        <div class="layui-input-inline">
                            <select name="pressStatus" id="pressStatus" lay-search>
                                <option></option>
                                <option value="2">通过审批的新闻</option>
                                <option value="1">未审批的新闻</option>
                                <option value="3">被退回的新闻</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <label class="layui-form-label">置顶：</label>
                            <div class="layui-input-inline">
                                <select name="pressTop" id="pressTop" lay-search>
                                    <option value=""></option>
                                    <option value="1">置顶</option>
                                    <option value="2">未置顶</option>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <div class="layui-input-inline">
                        <label class="layui-form-label">新闻板块</label>
                        <div class="layui-input-inline">
                            <select name="motifId" class="layui-select" lay-verify="" lay-search="" id="motifID">
                                <option value=""></option>
                                <option value="0">互联网</option>
                                <option value="1">军事</option>
                                <option value="2">国内</option>
                                <option value="3">国际</option>
                                <option value="4">体育</option>
                                <option value="5">娱乐</option>
                                <option value="6">财经</option>
                                <option value="7">科技</option>
                                <option value="8">其他</option>
                            </select>
                        </div>
                    </div>
                    <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i>
                    </button>
                </form>
            </div>
        </fieldset>
    </div>
    <table class="layui-hide" id="table" lay-filter="test"></table>
    <c:if test="${sessionScope.user.userStatus==1}">
        <%--表格头部工具栏--%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal" lay-event="insertPress">发布新闻</button>
                <button class="layui-btn layui-btn-danger" lay-event="deletePressAll">批量删除</button>
                    <%--            <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>--%>
            </div>
        </script>
        <%--操作--%>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>

    </c:if>
    <c:if test="${sessionScope.user.userStatus==2}">
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="query">新闻详情</a>
                <%--<a class="layui-btn layui-btn-xs" lay-event="edit"></a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
        </script>
    </c:if>
    <c:if test="${sessionScope.user.userStatus==0}">
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="through">通过</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="rejected">驳回</a>
        </script
    </c:if>
</div>
</body>
<%--修改页面--%>
<div id="updatePress" style="display: none; padding: 20px;">
    <form class="layui-form" method="post" id="update">
        <div class="layui-form-item">
            <label class="layui-form-label">新闻标题</label>
            <div class="layui-input-block">
                <input type="text" id="pressHead" name="pressHead" value=""
                       placeholder="请输入要修改的新闻标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">创建人</label>
            <div class="layui-input-block">
                <input type="text" id="person" name="pressPerson"
                       placeholder="请输入要修改的创建人" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text ">
            <label class="layui-form-label">摘要</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入要修改的摘要内容" class="layui-textarea" id="pressAbstract"
                          name="pressAbstract"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入要修改的新闻内容" class="layui-textarea" id="pressContent"
                          name="pressContent"></textarea>
            </div>
        </div>
        <%--指定是否置顶--%>
        <div class="layui-form-item">
            <label class="layui-form-label">是否置顶</label>
            <div class="layui-input-block">
                <input type="checkbox" lay-filter="filter" value="" id="top" name="pressTop" lay-skin="switch">

            </div>
        </div>
        <%--设置新闻状态--%>
        <%--<div class="layui-form-item" id="status">
            <label class="layui-form-label">新闻状态</label>
            <div class="layui-input-block">
                <input type="radio" lay-filter="pressStatus" id="back" name="pressStatus" value="3" title="退回">
                <input type="radio" lay-filter="pressStatus" id="through" name="pressStatus" value="2" title="通过">
            </div>
        </div>--%>
        <div class="layui-form-item" style="text-align:center;">
            <div id="err_msg" style="color: red;font-size: 15px"></div>
            <input class="layui-btn" type="submit" lay-filter="update" lay-submit=""/>
            <button type="button" id="cancelUpdate" class="layui-btn layui-btn-normal">取消修改</button>
        </div>
    </form>
</div>
<%--新闻详情--%>
<div id="pressDetails" style="display: none; padding: 20px">
    <%--新闻标题--%>
    <h1 class="pressHead"></h1>
    <%--创建时间--%>
    发布时间: <span class="pressDate"></span>作者：<span class="press_username"></span>
    <hr>
    <p class="press_abs"></p>
    <p class="press_content"></p>
</div>
<%--新增页面--%>
<%--<div id="insertPress" style="display: none; padding: 20px;">
</div>--%>
<script src="${pageContext.request.contextPath}/assets/js/allPress.js"></script>
</html>
