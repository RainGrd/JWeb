<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/22
  Time: 17:08:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--加入该标签手动开启el功能--%>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--c:if:用于做逻辑判断--%>
<c:if test="true">
    <h1>true</h1>
</c:if>
<c:if test="false">
    <h1>false</h1>
</c:if>
<%--获取数据做出逻辑判断--%>

<c:if test="${status==1}">
    启用
</c:if>
<c:if test="${status == 0}">
    禁用
</c:if>
<%--<c:choose>
    <h1>禁用</h1>
</c:choose>--%>
<%--<c:if test="${status==0}">
    <h1>禁用</h1>
</c:if--%>
</body>
</html>
