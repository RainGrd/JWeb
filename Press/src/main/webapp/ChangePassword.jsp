<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/5/23
  Time: 17:17:50
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
    <link
            rel="shortcut icon"
            type="images-icon"
            href="${pageContext.request.contextPath}/images/icon/doctor.ico"
    />
</head>
<body>


<script>
    layui.use('form', function () {
        var form = layui.form;

        //各种基于事件的操作，下面会有进一步介绍
    });
</script>
</body>
</html>