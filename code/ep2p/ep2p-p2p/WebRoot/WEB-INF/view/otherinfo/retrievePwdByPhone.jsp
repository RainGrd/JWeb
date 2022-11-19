<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/reset_pwd.js"></script> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>e生财富</title>
</head>
<body>
    <input type="hidden" name="phoneNo" id="phoneNo_" value="${phoneNo}">
	<div class="content mt40">
		<!--找会登录密码s-->
		<div class="login_z bgffffff w1140 m_auto pl15 pr15">
		  <c:if test="${empty flag}">
				<div class="ge_ye_tilte">
					<span class="ge_ye_tilte_nav  ms_t_se">找回登录密码</span>
				</div>
			</c:if>
			 <c:if test="${flag ==1}">
				<div class="ge_ye_tilte">
					<span class="ge_ye_tilte_nav  ms_t_se">找回交易密码</span>
				</div>
			</c:if>
			<div class="kong35"></div>
			<div class="kong150 colorDarkBlue">
				<p class="tc size24 lh150">
					请完成验证
				</p>
				<p class="size14 kong30 lh30 tc">
					<span class="inline_block login_z_in m_auto tl pos-r">
						<i class="i_ w95 inline_block tc colorDarkBlue">手机号码</i><input type="phoneNo" class="w220 pl10" id="phoneNoView" name="phoneNoView" value="${phoneNoStr}" disabled="disabled" placeholder="手机号码"/> <i class="i_ c2980b9 ml10 z_yanzheng" id="sendSmsCodeBu" >获取验证码</i>
					</span>
				</p>
				<p class="kong20"></p>
				<p class="size14 lh30 tc">
					<span class="inline_block login_z_in m_auto tl">
						<i class="i_ w95 inline_block tc colorDarkBlue"><i class="i_ colorc">*</i>验证码</i><input type="text" id="smsCode" name="smsCode" class="w220 pl10" placeholder="手机验证码" /> 
						<br>
						<p class="size14 c2980b9 lh60" style="text-align: center;" onclick="resetPwd.sendScheduledSMS();">获取语音验证码</p>
						<span class="input_tis ts_re mt5 block fr w215 " style="display:none; margin-right:91px;" id="smsCodeMes">
							<!--ts_re 是红色警告背景  
								ts_lh 是黄色警告背景
								ts_lv 是绿色正确提示背景-->
						</span>
					</span>
				</p>
				<p class="kong20"></p>
					<p class="size14 kong30 lh30 tc">
						<span class="inline_block login_z_in m_auto tl">
							<i class="i_ tc btn w270 ml10" id="resetPwdNext">
								下一步
							</i>						
						</span>
				</p>
			</div>
		</div>
		<div class="kong30"></div>
		<!--找会登录密码e-->
		<input type="hidden" id="loginName" name="loginName" value="${phoneNo}">
		<input type="hidden" id="flags" name="flag" value="${flag}">
		<!-- 用户发送短信 -->
		<input type="hidden" id="phoneNo" name="phoneNo" value="${phoneNo}">
	</div>
</body>
<script  type="text/javascript" >
$(function(){
	resetPwd.stepTwoInit();
})
</script>

</html>