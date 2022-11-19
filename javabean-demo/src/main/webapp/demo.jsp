<%@ page import="com.bdqn.entity.Manager" %>
<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/5/9
  Time: 10:09:30
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
    <title>Title</title>
</head>
<body>
<jsp:useBean id="manager"  class="com.bdqn.entity.Manager"/>
<%--<jsp:useBean id="managerr" scope="request" class="com.bdqn.entity.Manager" type="com.bdqn.entity.Manager">
    Body
</jsp:useBean>--%>
<%--<jsp:setProperty name="manager" property="bonus"/>
<%
    manager=(Manager)pageContext.getAttribute("manager");
    out.write("bonus属性的值为："+manager.getBonus());
%>--%>
<jsp:setProperty name="manager" property="*"/>
<%
    manager=(Manager)pageContext.getAttribute("manager");
    out.write("bonus属性的值为："+manager.getBonus());

%>
</body>
</html>
