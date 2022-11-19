<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
	<div class="goumai_vip_c">
	<div class="vip_g_b"></div>
	<div class="vip_g_c m_auto h350">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">修改登录密码</span>
		</div>
		<div class="kong35"></div>
		<div class="vip_g_lists m_auto colorDarkBlue size14">
			<p class="lh30 w360 m_auto">
						<span class="w95 inline_block tl">
							<i class="i_ colorc mr10">*</i>原密码:</span>
							<span class="">
								<input type="password" class="w220 pl10 " id="oldPwd" value="">
								<span class="input_tis ts_lh w200 mb5 ml99 tc" style="display: none" id="oldPwd_s" >
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
<!-- 										原登陆密码不能为空！/原登陆密码不正确！ -->
								</span>
							</span>
					</p>
					<p class="kong15"></p>
			<p class="lh30 w360 m_auto ">
						<span class="w95 inline_block tl">
							<i class="i_ colorc mr10">*</i>新密码:</span>
							<span class="">
								<input type="password" class="w220 pl10" value="" id="newPwd">
							<span class="input_tis ts_lh w200 mb5 ml99 tc " style="display: none" id="newPwd_s">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
<!-- 										新登陆密码不能为空！ -->
								</span>
							</span>
					</p>
					<p class="kong15"></p>
			<p class="lh30 w360 m_auto ">
						<span class="w95 inline_block tl"><i class="i_ colorc mr10">*</i>确认新密码:</span><span class="">
							<input type="password" class="w220 pl10 " value="" id="sureNewPwd">
						<span class="input_tis ts_lh w200 mb5 ml99 tc" style="display: none" id="sureNewPwd_s">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
<!-- 										确认登陆密码不能为空！确认登陆密码与新密码不一致！ -->
								</span>
						</span>
					</p>
			<p class="kong15"></p>
			<p class="lh45 w360 m_auto">
				<span class="w115 inline_block tr h10"></span><span class=""><a class="btn_samll ml15" href="javascript:updateLoginPwd.saveLoginPwd();">保存修改</a></span>
			</p>
			<div class="kong35"></div>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/securitycenter/toUpdateLoginPwdPage.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/rsa/RSA.js"></script>