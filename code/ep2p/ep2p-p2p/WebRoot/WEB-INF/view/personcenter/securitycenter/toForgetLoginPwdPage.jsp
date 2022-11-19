<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<div class="content mt40">
			<!--选择找回方式s-->
			<div class="login_z bgffffff w1140 m_auto pl15 pr15">
				<div class="ge_ye_tilte">
					<span class="ge_ye_tilte_nav  ms_t_se">找回登录密码</span>
				</div>
				<div class="kong35"></div>
				<div class="kong150 colorDarkBlue">
					<p class="tc size24 lh150">
						请选择找回方式
					</p>
					<p class="size14  lh30 tc w315 m_auto">
						<span class="inline_block login_h_in m_auto tl  block">
							<i class="i_ w75 inline_block tc colorDarkBlue tl fl">
								<i class="i_ colorc ">*</i>用户名</i>
								<input type="text" class="w220 pl10 fl" placeholder="用户名" name="userName" id="userName" value=""/> 
						</span>
<!-- 						<span class="input_tis ts_re w215 mt5 block fr mr5"> -->
							<!--ts_re 是红色警告背景  
								ts_lh 是黄色警告背景
								ts_lv 是绿色正确提示背景-->
<!-- 								系统存在的用户名 -->
<!-- 						</span> -->
					</p>
					<p class="kong1 cb"></p>
					<p class="kong20"></p>
					<p class="size14 kong30 lh30 tc w315 m_auto">
						<span class="inline_block login_h_in m_auto tl">
							<i class="i_ w75 inline_block tc colorDarkBlue tl fl_">
							<i class="i_ colorc fl_">*</i>验证码</i>
<!-- 							    <input type="text" class="w110 pl10 fl_" placeholder="验证码" /> -->
<!-- 							    <span type="text" class="fl_ ml10 login_mobile" placeholder="手机验证码"> </span> -->
								<input type="text" name="kaptcha" id="kaptcha"> 
								<img title = "点我换一张" alt="点我换一张" id="kaptchaImg" 
									src="${basePath}kaptcha.jpg" style="cursor:pointer;" onclick="common.changeVerifyCode();">
						</span>
					</p>
					<p class="kong20"></p>
					<p class="size14 kong30 lh30 tc select_js">
						<span class="inline_block login_h_in m_auto tl">
							<i class="radio_s radio_xiu1 m5 yu_b" val="1"><img src="/ep2p/theme/default/images/radio_x.png" class="block"></i>
							<i class="i_ select_c c2980b9">手机号码找回</i>
							<i class="radio_s radio_xiu1 m5 yu_b ml15" val="2"></i><i class="i_ select_c">邮箱找回密码</i>
						</span>
					</p>
					<p class="kong20 w315 "></p>
						<!--添加下一步 -->
						<p class="size14 kong30 lh30 tc">
							<span class="inline_block login_h_in m_auto tl">
								<i class="i_ tc btn w260 " onclick="forgerLoginPwd.next(1)">
									下一步
								</i>						
							</span>
					    </p>
					
						<p class="size14 kong30 lh30 tc none">
							<span class="inline_block login_h_in m_auto tl">
								<i class="i_ tc btn w260 ">
									完成
								</i>						
							</span>
					    </p>
					    
				</div>
			</div>
			<div class="kong30"></div>
			<!--选择找回方式 e-->

</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/securitycenter/toForgetLoginPwdPage.js"></script>
