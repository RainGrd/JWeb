<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/21
  Time: 20:02:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加品牌</title>
</head>
<link rel="stylesheet" type="text/css" href="assets/css/layui.css">
<link>
<script src="assets/layui.js"></script>
<body>
<%--<form action="/brand-demo/addBrandServlet" method="POST">
    品牌名称：<input id="brandName" name="brandName"><br>
    企业名称：<input id="companyName" name="companyName"><br>
    排序：<input id="ordered" name="ordered"><br>
    描述信息：<textarea rows="5" cols="20" id="description" name="description"></textarea><br>
    状态：
    <input type="radio" name="status" value="0">禁用
    <input type="radio" name="status" value="1">启用<br>

    <input type="submit" id="btn" value="提交">
</form>--%>
<div style="width: 500px; margin: 0px auto" >
    <h1 style="text-align: center">添加品牌</h1>
    <form class="layui-form layui-form-pane" action="/brand-demo/addBrandServlet" method="post" >
        <div class="layui-form-item">
            <label class="layui-form-label">品牌名称</label>
            <div class="layui-input-block">
                <input type="text" id="brandName" name="brandName" required  lay-verify="required" placeholder="品牌名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">企业名称</label>
            <div class="layui-input-block">
                <input type="text" id="companyName" name="companyName" required  lay-verify="required" placeholder="企业名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="text" id="ordered" name="ordered" required  lay-verify="required" placeholder="排序" autocomplete="off" class="layui-input">
            </div>
        </div>
<%--        <div class="layui-form-item">
            <label class="layui-form-label">选择框</label>
            <div class="layui-input-block">
                <select name="city" lay-verify="required">
                    <option value=""></option>
                    <option value="0">北京</option>
                    <option value="1">上海</option>
                    <option value="2">广州</option>
                    <option value="3">深圳</option>
                    <option value="4">杭州</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">复选框</label>
            <div class="layui-input-block">
                <input type="checkbox" name="like[write]" title="写作">
                <input type="checkbox" name="like[read]" title="阅读" checked>
                <input type="checkbox" name="like[dai]" title="发呆">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开关</label>
            <div class="layui-input-block">
                <input type="checkbox" name="switch" lay-skin="switch">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="status" value="0" title="禁用">
                <input type="radio" name="status" value="1" title="启用" checked>
            </div>
        </div>
        --%>
        <div class="layui-form-item layui-form-text ">
            <label class="layui-form-label" >描述信息</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" id="description" name="description"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input class="layui-btn layui-btn-normal" id="btn" type="submit" style="margin-left: 70px"/>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>