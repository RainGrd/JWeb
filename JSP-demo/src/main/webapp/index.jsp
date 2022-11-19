<%@ page import="java.nio.charset.StandardCharsets" %>
<%--
  Created by IntelliJ IDEA.
  User: RainGrd
  Date: 2022/4/28
  Time: 16:34:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<jsp:forward page="hello.jsp" />--%>
<h1>演示</h1>
<%
    //    int a=1/0;
    /*pageConText对象*/
    HttpServletRequest req= (HttpServletRequest) pageContext.getRequest();
    String remoteUser = req.getRemoteUser();
    System.out.println(remoteUser);
    String remoteAddr = req.getRemoteAddr();
    String remoteHost = req.getRemoteHost();
    System.out.println(remoteHost);
    System.out.println(remoteAddr);
    out.print(remoteAddr);
    out.print(remoteUser);
    pageContext.setAttribute("company","北京传智博客教育有限公司",pageContext.PAGE_SCOPE);
    Object company = pageContext.getAttribute("company", pageContext.PAGE_SCOPE);
    out.print(company);
    HttpServletResponse resp= (HttpServletResponse) pageContext.getResponse();
    Object page1 = pageContext.getPage();
    out.print("<br/>"+page1);
    /*for (int i = 1; i <=6 ; i++) {
        out.write("<h"+i+">"+"初来乍到"+"</h"+i+">");
    }*/
%>
</body>
</html>
