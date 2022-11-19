<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/reset_pwd.js"></script> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>e生财富</title>
</head>
<body>
	<form id="loginForm" action="" >
		<input type="hidden" id="flag_" value="${flag}"/>
		<div class="content mt40">
			<!--选择找回方式s-->
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
					<p class="tc size24 lh150">
						请选择找回方式
					</p>
					<p class="size14 lh30 tc">
						<span class="inline_block login_h_in m_auto tl">
							<i class="i_ w75 inline_block tc colorDarkBlue tl"><i class="i_ colorc">*</i>用户名</i><input type="text"  name="loginName" id="loginName" class="w220 pl10" placeholder="用户名"/> 
							<br />
							<span class="input_tis ts_re w215 mt5 block fr  mr5" style="display:none" id="loginNameMes">
								<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
							</span>
						</span>
						
					</p>
					<p class="kong20"></p>
					<p class="size14 lh30 tc">
						<span class="inline_block login_h_in m_auto tl">
							<i class="i_ w75 inline_block tc colorDarkBlue tl fl_">
								<i class="i_ colorc fl_">*</i>验证码</i>
								<input type="text" name="kaptcha" id="kaptcha" class="pl10 w150"> 
								<img title = "点我换一张" alt="点我换一张" id="kaptchaImg" 
									src="${basePath}kaptcha.jpg" style="cursor:pointer;" onclick="common.changeVerifyCode();">
							<br />
							<span class="input_tis ts_re w215 mt5 block fr  mr5" style="display:none" id="kaptchaMes">
								<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
							</span>
						</span>
						
						
					</p>
					<p class="kong20"></p>
					<p class="size14 kong30 lh30 tc select_js">
						<span class="inline_block login_h_in m_auto tl">
							<i class="radio_s radio_xiu1 m5 yu_b"  val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>
							<i class="i_ select_c c2980b9">手机号码找回</i>
							<i class="radio_s radio_xiu1 m5 yu_b ml15" val="2"></i><i class="i_ select_c">邮箱找回密码</i>
						</span>
					</p>
					<p class="kong20"></p>
						<p class="size14 kong30 lh30 tc">
							<span class="inline_block login_h_in m_auto tl">
								<i class="i_ tc btn w260 " onclick="resetPwd.nextStepOne()">
									下一步
								</i>						
							</span>
					    </p>
				</div>
			</div>
			<div class="kong30"></div>
			<!--选择找回方式 e-->
		</div>
	</form>
	<script>
		$(function(){
			resetPwd.init();
		})
	
	</script>
</body>
</html>