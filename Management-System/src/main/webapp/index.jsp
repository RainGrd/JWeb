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
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>首页</title>
  <script type="text/javascript" src="assets/js/JQuery.min.jS"></script>
  <!--导入CSS-->
  <link rel="stylesheet" href="assets/css/index.css" />
  <!-- 导入图标 -->
  <link rel="shortcut icon" type="image-icon" href="icon/doctor.ico" />
</head>
<body>
<header>
  <!-- top -->
  <div class="topbg">
    <!-- 导航条 -->
    <ul class="nav">
      <a href="index.jsp">Home</a>
      <a href="#">关于我们</a>
      <a href="login.jsp">登录</a>
      <a href="register.jsp">注册</a>
    </ul>
  </div>
  <!-- 华丽的分割线 -->
  <div class="topbgline"></div>
  <nav id="nav_ul">
    <ul>
      <li><a href="index.jsp" class="topnav_current">首页</a></li>
      <li><a href="#">关于我们</a></li>
      <li><a href="#">技术分享</a></li>
      <li><a href="person/index.html">关于我</a></li>
    </ul>
  </nav>
</header>
    <section>
      <!-- banner展示图 -->
      <div id="banner_Div">

      </div>
    </section>
    <!-- 底部 -->
<footer id="footer">
  <div><a href="index.jsp">返回首页</a></div>
</footer>
</body>
<script src="assets/js/index.js"></script>
</html>

