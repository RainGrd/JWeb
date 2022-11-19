<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>更换安全手机选择更换方式页面 -->
<div class="sidebar_b borrow_div fl">
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全手机</span>
		</div>
		<div class="lh110 colorDarkBlue size24">
			<p class="tc">
				您正在更换的绑定手机号码是<span class="colorc">${phoneEncryption}</span>
			</p>
		</div>
		<div class="lh58 colorDarkBlue size18 tc">
			<p class="hui_l_q cus_p">
				<a href="<%=basePath %>/login/userController/toSafetyVerifyMobilePage.shtml?phone=${phone}">使用原手机号+交易密码更换</a>
			</p>
			<p class="kong30"></p>
			<p class="hui_l_q cus_p">
				<a href="<%=basePath %>/login/userController/toSafetyVerifyIdForMobilPage.shtml">使用身份号码+交易密码更换</a>
			</p>
		</div>
	</div>
</div>