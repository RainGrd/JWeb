<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/yscf.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/reg.css" />
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/common/otherinfoCommon.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/registered.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/rsa/RSA.js"></script> 
<body>
	<form id="registeredForm" action="" >
		<!--头部s-->
		<div class="hui_lO bgffffff">
			<div class="m_auto w1200 pl30 pr30 kong75 ">
				<a href="#" onclick="common.toIndexPage();"><img src="<%=basePath %>theme/default/images/l_1.png" alt="e生财富" class="fl_" /></a>
				<span class="c5b5953 size24 fr_ lh75">
					400 998 992
				</span>
				<img src="<%=basePath %>theme/default/images/l_6.png" class="fr_ mr10" />
		    </div>
		</div>
		<!--头部e-->
		<!--主体s-->
		<div class="m_auto bgf2f6f8 ">
			<div class="kong90"></div>
			<div class="reg_bg m_auto pos-r">
				<img src="<%=basePath %>${filePath}" class="regBanner"/>
				<!--注册开始e-->
				<div class="reg_all pos-a bgffffff colorDarkBlue" id="stepOne">
					<p class="lh105 size14 tc colorc" id="message">
						&nbsp;
					</p>
					<div class="reg_in m_auto ">
						<p class="size14">
							<input type="text" placeholder="请输入手机号码"  id="phoneNo" type="phoneNo" class="w280 lh45 reg_a_in pl10"/>
						</p>
						<p class="kong20"></p>
						<p class="size14">
							<input type="password" placeholder="请输入密码"  id="password" type="password" class="w280 lh45 reg_a_in pl10" onchange="registered.valiPwd();"/>
						</p>
						<p class="size14 lh35">
							<span class="reg_an_b" id="pwdLength"> 6-32位字符</span><span class="reg_an_b" id="pwdStr">至少包含两种字符</span>
						</p>
						<p class="kong20"></p>
						<p class="size14   tc">
							<span class="inline_block login_h_in m_auto tl">
								<input type="text" name="kaptcha" id="kaptcha" placeholder="请输入验证码" class="w120 lh45 reg_a_in pl10 fl"> <img title = "点我换一张" alt="点我换一张" id="kaptchaImg" src="${basePath}kaptcha.jpg" class="fl ml30" style="cursor:pointer;height:49px;" onclick="common.changeVerifyCode();">
							</span>
					    </p>
					    <p class="kong20"></p>
					    
						<p class="size14">
							<input type="hidden" name="referralCode" value="${referralCode }" id="referral_code">
							<input type="text" placeholder="请输入推荐码(选填)" id="referralCode" name="referralCode" class="w280 lh45 reg_a_in pl10" value="${referralCode}"/>
						</p>
						<p class="kong50 size14 mt10 c2980b9">
							<span class="gou_s fl_ mr10" val="1" id="agreement"><img src="/ep2p/theme/default/images/gou_b.png" class="block"></span><i class="i_ c2980b9">同意《e生财富注册协议》</i>
						</p>
						<p class="size14 c2980b9  mt10 mb20">
							<span class="btn w240 lh26 ffffff  size16 reg_js_b" onclick="registered.next();">下一步</span>
						</p>
					</div>
				</div>
				
				<!--注册开始s-->
				<div class="reg_all pos-a bgffffff colorDarkBlue none" id="stepTwo">
					<p class="lh105 size14 tc colorc" id="stepTwoMessage">
						&nbsp;
					</p>
					<div class="reg_in m_auto ">
						<p class="size24 lh75" id="phoneStr">135****6384</p>
						<p class="size14">
							<span class="inline_block hui_l_q w100x lh40 reg_r">
								<i class="i_  w145">
									<input type="text" placeholder="输入验证码" id="smsCode"  class="reg_r_x pl10 ml10"/>
								</i>
								<i class="i_  ">|</i>
								<i class="i_ fr_ tc c2980b9 w140 reg_r_y cus_p" id="sendSmsCodeBu">
			 						发送验证码
								</i>
							</span>
						</p>
						<p class="kong30"></p>
						<p class="size14 colorDarkBlue">恭喜您，您的手机号码已经成功发送，请注意查收验证码。</p>
						<p class="kong30"></p>
						<p class="size14 colorDarkBlue">没收到短信？使用语音验证码进行手机验证。</p>
						<p class="size14 c2980b9 lh60" onclick="registered.sendScheduledSMS();">获取语音验证码</p>
						<p class="size14 c2980b9 kong10"></p>
						<p class="size14 c2980b9 mb20">
							<span class="btn w240 lh26 ffffff  size16 reg_js_a" onclick="registered.submit();">完成注册</span>
						</p>
					</div>
				</div>
				<!--注册验证s-->
				
				<!--注册验证e-->
				<!--完成注册s-->
				<div class="reg_all pos-a bgffffff colorDarkBlue none" id="complete">
					<p class="lh95 size24 tc">
						&nbsp;
					</p>
					<div class="reg_in m_auto ">
						<p class="kong35">
						</p>
						<p class="size24 lh75 tc">
							<img src="<%=basePath %>theme/default/images/r_4.png" />
						</p>
						<p class="kong35"></p>
						<p class="size18 colorDarkBlue tc">
							恭喜您,完成注册!
						</p>
						
						<p class="size14 c2980b9  tc">完善资料</p>
						<p class="size14 c2980b9 kong120"></p>
						<p class="size14 c2980b9 tc">
							<span class="btn w240 lh26 ffffff  size16" onclick="common.toIndexPage();">知道了</span>
						</p>
						<p class="size14 c2980b9 tc lh30">
							<strong id="endtime"></strong>秒后自动跳转到系统首页
						</p>
					</div>
				</div>
				<!--完成注册e-->
			</div>
			<div class="kong70"></div>
		</div>
		</form>
		<!--主体e-->
		<!--尾巴e-->
		<!--版权s-->
		<div c class="bg4c4c4c kong60 cf2f2f2">
			<p class="size14 tc lh60 cf2f2f2">
				Copyright © 2013 e生财富. All Rights Reserved  粤ICP备13050568号
			</p>
		</div>
		<!--版权e-->
		<input type="hidden" id="referralUser" name="referralUser" >
		<input type="hidden" id="flag" value="${flag}">
		<script type="text/javascript" >
			$(function(){
				registered.init();
			})
		</script>
</body>