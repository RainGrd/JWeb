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
				<c:if test="${empty flag}">
					<div class="ge_ye_tilte">
						<span class="ge_ye_tilte_nav  ms_t_se">找回登录密码</span>
					</div>
				</c:if>
				<c:if test="${flag=='1'}">
					<div class="ge_ye_tilte">
						<span class="ge_ye_tilte_nav  ms_t_se">找回交易密码</span>
					</div>
				</c:if>
			<div class="kong35"></div>
			<div class="kong150 colorDarkBlue">
				<div class="kong150 colorDarkBlue">
					<p class="tc size24 lh150">
						请验证邮箱
					</p>
					
					<p class="lh18 tc size14 colorDarkBlue">
						已经给您的邮箱${email},<br />发了密码修改邮件，请查收！
					</p>
					<i class="tc size14 lh120 c2980b9" id="sendEmailBu">
					</i>
				</div>
			</div>
		</div>
		<div class="kong30"></div>
		<!--找会登录密码e-->
		<input type="hidden" id="loginName" name="loginName" value="${loginName}">
	</div>
</body>
<script  type="text/javascript" >
$(function(){
	resetPwd.emailInit();
})
</script>

</html>