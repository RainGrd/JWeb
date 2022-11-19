<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/login.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e生财富</title>
</head>
<body>
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
			<div  class="w1140 m_auto pos-r h660">
				<div class="login bgffffff pos-a ">
					<div class="reg_jsa">
						<p class="kong105 size24 colorDarkBlue tc lh105">
							用户登录
						</p>
						<div class="login_i m_auto">
							<form action="" >
								<span class="input_tis ts_lh w244 mb5" style="display:none" id="loginMes">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
										用户名不正确！
								</span>
								<p class="kong20"></p>
								<p class="kong40 pos-r">
									<input type="text" placeholder="请输入账号" id="loginName" name="loginName" class=" pl40 login_i_z" value="${loginName}" style="box-sizing: content-box;"/>
									<i class="pos-a " style="left: 13px; top: 8px;">
										<img src="/ep2p/theme/default/images/l_4.png" />
									</i>
									
								</p>
								<span class="input_tis ts_re w244 mt5"  style="display:none" id="loginNameMes">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
										用户名不能为空！
								</span>
								<p class="kong20"></p>
								<p class="kong40 pos-r">
									<input type="password" placeholder="请输入密码"  id="password" type="password"  class=" pl40 login_i_m" value="" style="box-sizing: content-box;"/>
									<i class="pos-a " style="left: 13px; top: 8px;">
										<img src="/ep2p/theme/default/images/l_5.png" />
									</i>
								</p>
								<span class="input_tis ts_re w244 mt5" style="display:none" id="passwordMes">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
										密码不能为空！
								</span>
								<p class="kong20"></p>
								<p class="kong40 size14">
									<span class="gou_s fl_ mr10" val="1" id="isSaveCookie"><img src="<%=basePath %>theme/default/images/gou_b.png" class="block"></span>
									<i class="i_ c2980b9">记住我</i>
									<i class="i_ c2980b9 fr_" onclick="window.open('<%=basePath %>login/userController/toRetrievePage.shtml')">忘记密码?</i>
								</p>
								<div style="display:none" id="isViewKaptcha">
									<p class="kong40 size14">
										验证码：
										<input type="text" name="kaptcha" id="kaptcha" class="w100"> <img title = "点我换一张" alt="点我换一张" id="kaptchaImg" src="${basePath}kaptcha.jpg" style="cursor:pointer;" onclick="common.changeVerifyCode();">
									</p>
									<span class="input_tis ts_re w244 mt5"   style="display:none" id="kaptchaMes">
										<!--ts_re 是红色警告背景  
										ts_lh 是黄色警告背景
										ts_lv 是绿色正确提示背景-->
											验证码不能为空！
									</span>
									<p class="kong20"></p>
								</div>
								<p class="kong60 size14">
									<span  class="btn w220 bnb cus_p" value="登录" onclick="login.loginSubmit();">登录</span>
								</p>
								<p class="kong60 c2980b9 cus_p size14 tc" >
									<a class="c2980b9" href="<%=basePath %>login/userController/toRegistered.shtml">新用户注册</a>
								</p>
								<input type="hidden" id="viewKaptcha" >
							</form>
						</div>	
					</div>
			    </div>
				<a href="${url}"><img class="loginBanner" src="<%=basePath %>${filePath}" /></a>
				<%-- 				<a href="${url}"><img src="<%=basePath %>${filePath}" /></a> --%>
			</div>
		</div>
		
		<!--尾巴s-->
		<div class="bgf2f6f8">
			<div class="pos-r lo_f_c w1200 m_auto foot_bg">
				<img src="<%=basePath %>theme/default/images/l_3.png" alt="" class="lo_f_c_i pos-a" />
			</div>
			<img src="<%=basePath %>theme/default/images/l_7.png" class="lo_f_bg" height="251" />
			
		</div>
		<!--尾巴e-->
		<!--版权s-->
		<div c class="bg4c4c4c kong60 cf2f2f2">
			<p class="size14 tc lh60 cf2f2f2">
				Copyright © 2013 e生财富. All Rights Reserved  粤ICP备13050568号
			</p>
		</div>
		<!--版权e-->
</body>

<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/login.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/rsa/RSA.js"></script> 
<script type="text/javascript">
	$(function(){
		login.init();
		login.enter();
	})
</script>

</html>