<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/reset_pwd.js"></script> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>e生财富</title>
</head>
<body>
	<div class="content mt40">
		<!--找会登录密码s-->
		<div class="login_z bgffffff w1140 m_auto pl15 pr15">
			<p class="lh18 tc size14 colorDarkBlue">
				邮件链接过期啦。
			</p>
		</div>
		<div class="kong30"></div>
		<!--找会登录密码e-->
		<input type="hidden" id="loginName" name="loginName" value="${loginName}">
	</div>
</body>
<script  type="text/javascript" >
$(function(){
// 	resetPwd.stepTwoInit();
})
</script>

</html>