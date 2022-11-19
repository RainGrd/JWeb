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
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="assets/css/login.css">
    <script src="assets/js/JQuery.min.jS"></script>
    <script src="assets/layui/layui.js" charset="utf-8"></script>
    <script src="assets/js/jquery.cookie.js"></script>
    <script src="assets/js/axios.js"></script>
    <link rel="stylesheet" href="assets/layui/css/layui.css" media="all">
    <script src="assets/layui/jquery.particleground.min.js" charset="utf-8"></script>
    <link
            rel="shortcut icon"
            type="images-icon"
            href="images/icon/doctor.ico"
    />
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%--<style>
        .main-body {
            top: 50%;
            left: 50%;
            position: absolute;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            overflow: hidden;
        }

        .login-main .login-bottom .center .item input {
            display: inline-block;
            width: 227px;
            height: 22px;
            padding: 0;
            position: absolute;
            border: 0;
            outline: 0;
            font-size: 14px;
            letter-spacing: 0;
        }

        .login-main .login-bottom .center .item .icon-1 {
            background: url(images/icon-login.png) no-repeat 1px 0;
        }

        .login-main .login-bottom .center .item .icon-2 {
            background: url(images/icon-login.png) no-repeat -54px 0;
        }

        .login-main .login-bottom .center .item .icon-3 {
            background: url(images/icon-login.png) no-repeat -106px 0;
        }

        .login-main .login-bottom .center .item .icon-4 {
            background: url(images/icon-login.png) no-repeat 0 -43px;
            position: absolute;
            right: -10px;
            cursor: pointer;
        }
        .login-main .login-bottom .center .item .icon-5 {
            background: url(images/icon-login.png) no-repeat -55px -43px;
        }
        .login-main .login-bottom .center .item .icon-6 {
            background: url(images/icon-login.png) no-repeat 0 -93px;
            position: absolute;
            right: -10px;
            margin-top: 8px;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .icon-nocheck {
            display: inline-block;
            width: 10px;
            height: 10px;
            border-radius: 2px;
            border: solid 1px #9abcda;
            position: relative;
            top: 2px;
            margin: 1px 8px 1px 1px;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .icon-check {
            margin: 0 7px 0 0;
            width: 14px;
            height: 14px;
            border: none;
            background: url(images/icon-login.png) no-repeat -111px -48px;
        }

        .login-main .login-bottom .center .item .icon {
            display: inline-block;
            width: 33px;
            height: 22px;
        }

        .login-main .login-bottom .center .item {
            width: 288px;
            height: 35px;
            border-bottom: 1px solid #dae1e6;
            margin-bottom: 35px;
        }

        .login-main {
            width: 428px;
            position: relative;
            float: left;
        }

        .login-main .login-top {
            height: 117px;
            background-color: #148be4;
            border-radius: 12px 12px 0 0;
            font-family: SourceHanSansCN-Regular;
            font-size: 30px;
            font-weight: 400;
            font-stretch: normal;
            letter-spacing: 0;
            color: #fff;
            line-height: 117px;
            text-align: center;
            overflow: hidden;
            -webkit-transform: rotate(0);
            -moz-transform: rotate(0);
            -ms-transform: rotate(0);
            -o-transform: rotate(0);
            transform: rotate(0);
        }

        .login-main .login-top .bg1 {
            display: inline-block;
            width: 74px;
            height: 74px;
            background: #fff;
            opacity: .1;
            border-radius: 0 74px 0 0;
            position: absolute;
            left: 0;
            top: 43px;
        }

        .login-main .login-top .bg2 {
            display: inline-block;
            width: 94px;
            height: 94px;
            background: #fff;
            opacity: .1;
            border-radius: 50%;
            position: absolute;
            right: -16px;
            top: -16px;
        }

        .login-main .login-bottom {
            width: 428px;
            background: #fff;
            border-radius: 0 0 12px 12px;
            padding-bottom: 53px;
        }

        .login-main .login-bottom .center {
            width: 288px;
            margin: 0 auto;
            padding-top: 40px;
            padding-bottom: 15px;
            position: relative;
        }

        .login-main .login-bottom .tip {
            clear: both;
            height: 16px;
            line-height: 16px;
            width: 288px;
            margin: 0 auto;
        }

        body {
            background: url(images/loginbg.png) 0% 0% / cover no-repeat;
            position: static;
            font-size: 12px;
        }

        input::-webkit-input-placeholder {
            color: #a6aebf;
        }

        input::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #a6aebf;
        }

        input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #a6aebf;
        }

        input:-ms-input-placeholder { /* Internet Explorer 10-11 */
            color: #a6aebf;
        }

        input:-webkit-autofill { /* 取消Chrome记住密码的背景颜色 */
            -webkit-box-shadow: 0 0 0 1000px white inset !important;
        }

        html {
            height: 100%;
        }

        .login-main .login-bottom .tip {
            clear: both;
            height: 16px;
            line-height: 16px;
            width: 288px;
            margin: 0 auto;
        }

        .login-main .login-bottom .tip .login-tip {
            font-family: MicrosoftYaHei;
            font-size: 12px;
            font-weight: 400;
            font-stretch: normal;
            letter-spacing: 0;
            color: #9abcda;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .forget-password {
            font-stretch: normal;
            letter-spacing: 0;
            color: #1391ff;
            text-decoration: none;
            position: absolute;
            right: 62px;
        }

        .login-main .login-bottom .login-btn {
            width: 288px;
            height: 40px;
            background-color: #1E9FFF;
            border-radius: 16px;
            margin: 24px auto 0;
            text-align: center;
            line-height: 40px;
            color: #fff;
            font-size: 14px;
            letter-spacing: 0;
            cursor: pointer;
            border: none;
        }

        .login-main .login-bottom .center .item .validateImg {
            position: absolute;
            right: 1px;
            cursor: pointer;
            height: 36px;
            border: 1px solid #e6e6e6;
        }

        .footer {
            left: 0;
            bottom: 0;
            color: #fff;
            width: 100%;
            position: absolute;
            text-align: center;
            line-height: 30px;
            padding-bottom: 10px;
            text-shadow: #000 0.1em 0.1em 0.1em;
            font-size: 14px;
        }

        .padding-5 {
            padding: 5px !important;
        }

        .footer a, .footer span {
            color: #fff;
        }

        @media screen and (max-width: 428px) {
            .login-main {
                width: 360px !important;
            }

            .login-main .login-top {
                width: 360px !important;
            }

            .login-main .login-bottom {
                width: 360px !important;
            }
        }
    </style>--%>
</head>
<body>
<%--<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>login</title>
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/layui/layui.js"></script>
    <script src="assets/js/JQuery.min.jS"></script>
    <script src="assets/js/jquery.cookie.js"></script>
    <script src="assets/js/tools.js"></script>
    <script src="assets/js/axios.js"></script>
    <script src="assets/layui/jquery.particleground.min.js" charset="utf-8"></script>
    <link
            rel="shortcut icon"
            type="images-icon"
            href="images/icon/doctor.ico"
    />
</head>
&lt;%&ndash;<style>
    .layui-form-label{
        position: relative;
        float: left;
        padding: 9px 0px;
        width: 42px;
        font-weight: 400;
        line-height: 20px;
    }
</style>&ndash;%&gt;
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="/Management-System/loginServlet" method="POST" id="form">
                <div class="layui-form-item"><h1 id="loginMsg">LOGIN IN</h1></div>
                <div class="layui-form-item">

                    <label class="layui-form-label layui-icon-username">邮箱</label>
                    &lt;%&ndash;<div class="layui-input-block">

                    </div>&ndash;%&gt;
                    <input id="username" name="username" placeholder="请输入邮箱" class="layui-input" type="text" value=""/>
                    <div id="userName_err" class="errorMsg">${error}</div>
                </div>
                &lt;%&ndash;<div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <input
                            id="password"
                            class="layui-input"
                            name="password"
                            type="password"
                            value=""
                    />
                </div>&ndash;%&gt;
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password"  id="password" name="password" lay-verify="required|password" placeholder="密码"
                           autocomplete="off" class="layui-input" value="123456">
                </div>
                <div id="password_err" class="errorMsg"></div>
                <div class="layui-form-item">
                    <input type="checkbox" id="user_remember_me" name="rememberMe" value="true" lay-filter="filter" lay-skin="primary" title="记住我">
                </div>
                &lt;%&ndash;<div class="layui-form-item">
                    <label class="layui-form-label" for="user_remember_me">记住我</label>
                    <div class="layui-input-block">
                    </div>
                    <input
                            type="checkbox"
                            value="1"
                            id="user_remember_me"
                            tabindex="0"
                            class="hidden"
                    />
                    &lt;%&ndash;注册按钮&ndash;%&gt;
                    <div id="register">
                        <b></b>
                        <a href="register.jsp">没有账号？点击注册</a>
                    </div>
                </div>&ndash;%&gt;
                &lt;%&ndash;<div class="fixed">
                    <!-- 复选框 -->
                    <div class="checkbox">
                        <label for="user_remember_me">记住我</label>
                    </div>
                </div>&ndash;%&gt;
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left: 0px" id="subDiv">
                        <!--登录按钮-->
                        <input type="submit" class="layui-btn layui-btn-normal button" value="login up"/>
                    </div>
                </div>
                <div>

                </div>
            </form>
        </div>
    </div>
</div>
&lt;%&ndash;<div id="loginDiv" >
    <form class="layui-form"  action="/Management-System/loginServlet" method="POST" id="form">
        <div class="layui-form-item"><h1 id="loginMsg">LOGIN IN</h1></div>
        <div class="layui-form-item">

            <label class="layui-form-label" >邮箱</label>
            <div class="layui-input-block" style="margin-left: 70px">
                <input id="username" name="username" placeholder="请输入邮箱" class="layui-input" type="text" value=""/>
            </div>
            <div id="userName_err" class="errorMsg">${error}</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block"  style="margin-left: 70px">
                <input
                        id="password"
                        class="layui-input"
                        name="password"
                        type="password"
                        value=""
                />
            </div>
            <div id="password_err" class="errorMsg"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="user_remember_me">记住我</label>
            <div class="layui-input-block"  style="margin-left: 70px">
                <input
                        type="checkbox"
                        style="display: inline-block"
                        value="1"
                        id="user_remember_me"
                        tabindex="0"
                        class="hidden"
                />
                &lt;%&ndash;注册按钮&ndash;%&gt;
                <div id="register">
                    <b></b>
                    <a href="register.jsp">没有账号？点击注册</a>
                </div>

            </div>
        </div>
        <div class="fixed">
            <!-- 复选框 -->
            <div class="checkbox">

&lt;%&ndash;                <label for="user_remember_me">记住我</label>&ndash;%&gt;
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 0px" id="subDiv">
                <!--登录按钮-->
                <input type="submit" class="layui-btn layui-btn-normal button" value="login up"/>
            </div>
        </div>
        <div >

        </div>
    </form>
</div>&ndash;%&gt;
</body>
<script src="assets/js/login.js"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;
        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function () {
            $('.layui-container').particleground({
                dotColor: '#7ec7fd',
                lineColor: '#7ec7fd'
            });
        });
        form.on('checkbox(filter)', function(data){
            // console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //是否被选中，true或者false
            // console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            // console.log(data.othis); //得到美化后的DOM对象
        });
        // 进行登录操作
        /*form.on('submit(login)', function (data) {
            data = data.field;
            if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if (data.captcha == '') {
                layer.msg('验证码不能为空');
                return false;
            }
            layer.msg('登录成功', function () {
                window.location = '../index.html';
            });
            return false;
        });*/
    });
</script>
</html>--%>
<div class="main-body">
    <div class="login-main">
        <div class="login-top">
            <span>登录-LOGIN</span>
            <span class="bg1"></span>
            <span class="bg2"></span>
        </div>
        <form class="layui-form login-bottom" id="form">
            <div class="center">
                <div class="item">
                    <span class="icon icon-2"></span>
                    <input type="text" id="username"   name="username" placeholder="请输入登录邮箱"
                           maxlength="24"/>
                    <br/>

                </div>
                <span id="userName_err" class="errorMsg"></span>
                <div class="item">
                    <span class="icon icon-3"></span>
                    <input type="password" id="password" autofocus="autofocus" name="password" placeholder="请输入密码"
                           maxlength="20">
                    <span class="bind-password icon icon-4"></span>
                </div>
                <span id="password_err" class="errorMsg"></span>
                <div class="layui-form-item" style="text-align:center;">
                    <input type="checkbox" name="identity" lay-filter="oneCheck" value="1" lay-skin="primary" title="管理员">
                    <input type="checkbox" name="identity" lay-filter="oneCheck" value="2" lay-skin="primary" title="教师">
                    <input type="checkbox" name="identity" lay-filter="oneCheck" value="3" lay-skin="primary" title="学生">
                </div>
            </div>
            <div class="tip">
                <div class="layui-form-item">
                    <input type="checkbox" id="user_remember_me" name="rememberMe" value="1" lay-filter="filter"  title="记住我">
                    <div id="register">
                        <b></b>
                        <a href="register.jsp">没有账号？点击注册</a>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="text-align:center; width:100%;height:100%;margin:0px;">
                <input type="submit" id="login" class="login-btn" lay-submit="" lay-filter="login" value="login up"/>
            </div>
        </form>
    </div>
</div>
<div class="footer">
    ©版权所有 2014-2018 叁贰柒工作室<span class="padding-5">|</span><a target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备16006642号-2</a>
</div>
<script src="assets/js/login.js"></script>
</body>
</html>
