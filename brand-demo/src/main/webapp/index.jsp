<%@ page import="com.itheima.pojo.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--加入该标签手动开启el功能--%>
<%@page isELIgnored="false" %>

<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/21
  Time: 20:02:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/JQuery.min.jS"></script>
<link rel="stylesheet" type="text/css" href="assets/css/layui.css" media="all">
<script src="assets/layui.js" charset="utf-8"></script>
<script src="js/jquery.cookie.js"></script>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书管理页面</title>
</head>
<style>
    .layui-table th {
        text-align: center;
    }

    .content {
        width: 1300px;
        margin: 0 auto;
    }

    #thisForm {
        text-align: center;
        margin: 20px 0;
    }

    .input_operation input {
        display: inline;
        width: 50px;
        padding: 0px;
    }
</style>
<body>
<div class="content">
    <form class="layui-form layui-form-pane" id="thisForm" action="/brand-demo/selectServlet" method="post">
        <div class="layui-form-item">
            <input type="text" id="selectBrand" name="selectBrand" placeholder="查询"
                   autocomplete="off" class="layui-input" style="width: 20%; display: inline-block">
            <input class="layui-btn" type="submit" value="查询"/>
            <a href="addBrand.jsp"><input class="layui-btn layui-btn-normal" type="button" value="添加"/></a>
            <%--<input class="layui-btn layui-btn-normal" type="button" value="导出文件"/>--%>
            <button type="button" class="layui-btn" id="export"><i class="layui-icon layui-icon-download-circle"></i>导出文件</button>
<%--            <button type="button" class="layui-btn" id="upload"><i class="layui-icon"></i>上传文件</button>--%>
        </div>
    </form>
    <table border="1" cellpadding="0" cellspacing="0" id="tableData" class="layui-table" lay-size="lg">
        <tr>
            <th>选中状态</th>
            <th>序号</th>
            <th width="100px">品牌名称</th>
            <th>企业名称</th>
            <th>品牌介绍</th>
            <th>排序</th>
            <th width="120px">操作</th>
        </tr>

        <c:forEach items="${brands}" var="brand" varStatus="brand_id">
            <tr align="center">
                <td>
                    <div class="layui-form-item">
                        <input type="checkbox" name="checkbox${brand.id}" lay-skin="primary"
                               class="layui-unselect layui-form-checkbox layui-form-checked checkbox">
                    </div>
                </td>
                <td>${(brand_id.index+1)}</td>
                <td>${brand.brandName}</td>
                <td>${brand.companyName}</td>
                <td>${brand.description}</td>
                <td>${brand.ordered}</td>
                <td class="input_operation">
                    <%--<input  name="" type="button" class=" layui-btn layui-btn-normal"
                           value="修改"/>--%>
                    <button data-type="update" name="${brand.id}" class="layui-btn layui-btn-normal layui-btn-sm update"><i class="layui-icon">&#xe642;</i></button>
<%--                    <input type="button" class="layui-btn layui-btn-danger delete" value="删除"/>--%>
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger delete" >
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--菜单选择区--%>
    <div class="layui-btn-container" style="float:right;">
        <%--        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>--%>
        <button class="layui-btn layui-btn-sm batchOperation" lay-event="getCheckLength">批量删除</button>
        <button class="layui-btn layui-btn-sm isAll">全选</button>
        <button class="layui-btn layui-btn-sm cancelAll">取消全选</button>
    </div>
</div>
<%--修改页面--%>
<div id="update" style="display: none; padding: 20px;">
    <form class="layui-form layui-form-pane" action="/brand-demo/updateServlet" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">品牌名称</label>
            <div class="layui-input-block">
                <input style="display: none" type="text" id="brandId" name="id" value="">
                <input type="text" id="brandName" name="brandName" value="" required lay-verify="required"
                       placeholder="请输入要修改的品牌名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">企业名称</label>
            <div class="layui-input-block">
                <input type="text" id="companyName" name="companyName" required lay-verify="required"
                       placeholder="请输入要修改的企业名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="text" id="ordered" name="ordered" required lay-verify="required" placeholder="请输入要修改的排序"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text ">
            <label class="layui-form-label">描述信息</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入要修改的内容" class="layui-textarea" id="description" name="description"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align:center;">
            <div id="err_msg" style="color: red;font-size: 15px"></div>
            <input class="layui-btn" id="btn" type="submit"/>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>
</body>
<script src="js/index.js">

</script>
<script>
    $("#export").on("click",function (){
        location.href="http://localhost:8080/brand-demo/exportExcelServlet"
    })
</script>
</html>
