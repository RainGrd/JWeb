<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}">
<title>欢迎登陆</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link type="text/css" href="${basePath}resources/plugins/jquery-easyui-1.4.2/themes/default/easyui.css"></link>
<link type="text/css" href="${basePath}resources/plugins/jquery-easyui-1.4.2/themes/icon.css"></link>

<link rel="shortcut icon" type="image/x-icon" href="${basePath}resources/images/favicon.ico" media="screen" />

<link rel="stylesheet" type="text/css" href="${basePath}resources/css/login.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/tipsy.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/buttons.css">

<link type="text/css" href="${basePath}resources/plugins/jquery-easyui-1.4.2/themes/default/easyui.css"></link>
<link type="text/css" href="${basePath}resources/plugins/jquery-easyui-1.4.2/themes/icon.css"></link>



<script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>


<script type="text/javascript" src="${basePath}resources/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${basePath}resources/plugins/iphone.check.js"></script>
<script type="text/javascript" src="${basePath}resources/plugins/jquery-jrumble.js"></script>
<script type="text/javascript" src="${basePath}resources/plugins/jquery.tipsy.js"></script>
<script type="text/javascript" src="${basePath}resources/js/login.js"></script>
<script type="text/javascript" src="${basePath}resources/plugins/base64.js"></script>

<script type="text/javascript">
var BASE_PATH = "${basePath}";	
	if (top != self) {
		if (top.location != self.location)
			top.location = self.location;
	}
	$(document).ready(function(){
		$('body').css({'overflow':'hidden','height':document.documentElement.clientHeight+'px'});
	});
</script>

</head>
<body class="loginBg">
	<div id="discuzLogin">
		
	</div>
	<div class="text_success">
		<img src="${basePath}resources/images/loader_green.gif" alt="登陆成功!请稍后...." /> <span>登陆成功!请稍后....</span>
	</div>
	<div id="login">
		<div class="inner">
			<div class="logo" style="text-align:center;">
				<img src="${basePath}resources/images/login-logo.png" />
			</div>
			<div class="formLogin">
				<form id="formLogin" name="formLogin" action="sysUserController/login.shtml"
					check="sysUserController/checkUserLogin.shtml" id="formLogin" method="post">
					<input name="userKey" type="hidden" id="userKey" value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900" />
					<div class="tip">
						<span class="usericon"></span>
						<input class="usercss inputtxt" name="userName" type="text" id="userName"  title="用户名" iscookie="true"  nullmsg="请输入用户名!" />
						<input  name="userNameBase" type="hidden" id="userNameBase" />
					</div>
					<div class="tip">
						<span class="pwdicon"></span>
						<input class="pwdcss inputtxt" name="password" type="password" id="password" title="密码" iscookie="true"  nullmsg="请输入密码!" />
						<input name="passwordBase" type="hidden" id="passwordBase"/>
					</div>
					<div class="loginButton">
						 <div class="remeberDiv">
							<input type="checkbox"  class="none remember" name="remember" value="1"/>
							<label class="checkboxLabel ischeckLabel"></label>
							<span>记住我</span>
						</div>
						<div class="submitDiv">
							<div>
								<ul class="uibutton-group">
									<li><a class="uibutton normal" href="javascript:void(0);" id="but_login">登录</a></li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</form>
			</div>
			<div id="errorMessage" class="none"></div>
			<div id="successLogin"></div>
		</div>
	</div>
	<!--Login div-->
	<div class="clear"></div>
	<div id="versionBar">版权所有 © 深圳市e生财富 未经许可不得复制、转载或摘编，违者必究！</div>
</body>
</html>
