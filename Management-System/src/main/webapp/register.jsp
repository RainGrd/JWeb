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
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="assets/css/register.css" rel="stylesheet">
    <!-- 导入图标 -->
    <link rel="shortcut icon" type="image-icon" href="icon/doctor.ico"/>
    <script src="assets/js/axios.js"></script>
    <script src="assets/js/JQuery.min.jS"></script>
    <script src="assets/js/tools.js"></script>
</head>
<body>
<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form action="/Management-System/registerServlet" id="form" method="POST">
        <p class="inputs">
            <span>用户名</span>:&nbsp;&nbsp;&nbsp;
            <input name="email" type="text" id="userName">
            <br/>
            <span id="userName_err" class="err_msg"></span>
        </p>
        <p class="inputs">
            <span>邮箱</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="email" type="text" id="emailName">
            <br/>
            <span id="emailName_err" class="err_msg"></span>
        </p>
        <p class="inputs">
            <span>密码</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="password" type="password" id="password">
            <br/>
            <span id="password_err" class="err_msg"></span>
        </p>
        <p class="inputs">
            手机号码：
            <input name="phone" type="text" id="phone">
            <br/>
            <span id="tel_err" class="err_msg"></span>
        </p>
        <!--<p class="inputs">
            验证码：
            <input name="checkCode" type="text" id="checkCode">&nbsp;
            <img id="verify_img" src="/items/checkCodeServlet">
            <a href="#" id="changeImg">换一张?</a>
            <br/>
            <span id="checkCode_err" class="err_msg"></span>
        </p>-->
        <div class="buttons">
            <span class="err_msg" style="text-indent: 6em"></span>
            <input value="注 册" type="submit" class="button" id="reg_btn" style="margin-left: 100px">
        </div>
    </form>
</div>
<!-- 底部 -->
</body>
<script src="assets/js/register.js"></script>
</html>
