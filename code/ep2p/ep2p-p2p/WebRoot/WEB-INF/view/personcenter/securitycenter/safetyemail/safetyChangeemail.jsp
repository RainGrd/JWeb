<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>选择更换页面方式页面 -->
<div class="sidebar_b borrow_div fl">
	<!--  更换安全手机 s-->
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全邮箱</span>
		</div>
		<div class="lh110 colorDarkBlue size24">
			<p class="tc">
				您正在更换的安全邮箱是<span class="colorc">${encryptionEmail}</span>
			</p>
		</div>
		<div class="lh58 colorDarkBlue size18 tc">
		
			<p class="hui_l_q cus_p" ><a href="<%=basePath %>/login/userController/toSafetyVerifyeMailforEmailPage.shtml?email=${email}">使用原邮箱更换</a></p>
			<p class="kong30"></p>
			<p class="hui_l_q cus_p"><a href="<%=basePath %>/login/userController/toSafetyVerifyMobileforeMailPage.shtml">使用手机号码更换</a></p>
		</div>
	</div>
</div>

