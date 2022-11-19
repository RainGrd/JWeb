<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/common/otherinfoCommon.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/reset_pwd.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/rsa/RSA.js"></script> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>e生财富</title>
</head>
<body>
	<div class="content mt40">
		<!--重置密码s-->
		<div class="login_z bgffffff w1140 m_auto pl15 pr15">
			<div class="ge_ye_tilte">
				<span class="ge_ye_tilte_nav  ms_t_se">找回交易密码</span>
			</div>
			<div class="kong35"></div>
			<div class="kong150 colorDarkBlue">
				<p class="tc size24 lh150">
					请重置密码
				</p>
				<p class="size14 w335 lh30 tc m_auto oh">
						<span class="inline_block login_q_in m_auto tl">
							<i class="i_ w95 inline_block tc colorDarkBlue tl"><i class="i_ colorc">*</i>新密码</i><input type="password"  name="newPassword" id="newPassword" class="w220 pl10" placeholder="新密码" style="height:30px;"/> 
						</span>
						<span class="input_tis ts_lh w200 mb5 block fl" style="margin-left: 95px; display: none" id="newPassword_s">
							<!--ts_re 是红色警告背景  
							ts_lh 是黄色警告背景
							ts_lv 是绿色正确提示背景-->
<!-- 								手机号码不正确！ -->
						</span>
					</p>
				<p class="kong20"></p>
				<p class="size14 w335 lh30 tc m_auto oh">
						<span class="inline_block login_q_in m_auto tl">
							<i class="i_ w95 inline_block tc colorDarkBlue tl"><i class="i_ colorc">*</i>确认新密码</i><input type="password" name="newPasswordTo" id="newPasswordTo" class="w220 pl10" placeholder="确认新密码" style="height:30px;"/> 
						</span>
						<span class="input_tis ts_lh w200 mb5 block fl" style="margin-left: 95px; display: none" id="newPasswordTo_s">
							<!--ts_re 是红色警告背景  
							ts_lh 是黄色警告背景
							ts_lv 是绿色正确提示背景-->
<!-- 								手机号码不正确！ -->
						</span>
					</p>
				<p class="kong20"></p>
					<p class="size14 kong30 lh30 tc">
						<span class="inline_block login_q_in m_auto tl">
							<i class="i_ tc btn w270 ml10" onclick="resetPwd.resetTradPwd()">
								完成
							</i>						
						</span>
				</p>
			</div>
		</div>
		<div class="kong30"></div>
		<!--重置密码e-->
		<input type="hidden" id="loginName" name="loginName" value="${loginName}">
	</div>
</body>
</html>