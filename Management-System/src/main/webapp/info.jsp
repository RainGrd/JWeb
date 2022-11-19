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
    <title>个人信息</title>
    <%--移入CSS样式--%>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script src="js/JQuery.min.jS"></script>
</head>
<body>
<h1>${user.username}，欢迎您！</h1>
<%
%>

</body>
<%--引入JS--%>
<script src="assets/js"></script>
</html>
