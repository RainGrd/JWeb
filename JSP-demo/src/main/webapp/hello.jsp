<%@ page import="java.util.Date" %>
<%@ page import="sun.util.resources.LocaleData" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/28
  Time: 18:41:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String format = simpleDateFormat.format(date);
    out.println();
    out.print("当前时间："+format);

%>
</body>
</html>
