<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>更换原手机号码页面 -->
<div class="sidebar_b borrow_div fl">
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全手机</span>
		</div>
		<div class="gh_m_ico size14 colorDarkBlue hui_l">
			<div class="kong30"></div>
			<div class="gh_m_ico_c fl_">
				<span class="gh_m_ico_list tc"> 
					<img src="<%=basePath%>theme/default/images/an_9.png" class="tc" />
					<span class="lh55 ">验证原手机</span>
				</span>
			</div>
			<div class="gh_m_ico_c fl_ w330">
				<p class="hui_l kong30"></p>
			</div>
			<div class="gh_m_ico_c fl_">
				<span class="gh_m_ico_list tc"> 
					<img src="<%=basePath%>theme/default/images/an_8.png" class="tc" />
					<span class="lh55 ">验证新手机</span>
				</span>
			</div>
			<div class="gh_m_ico_c fl_ w330">
				<p class="hui_l kong30"></p>
			</div>
			<div class="gh_m_ico_c fl_">
				<span class="gh_m_ico_list tc">
					<img src="<%=basePath%>theme/default/images/an_7.png" class="tc" />
					<span class="lh55 ">成功</span>
				</span>
			</div>
		</div>
		<div class=" hui_l size14 colorDarkBlue">
			<p class="kong20"></p>
			<p class="lh45 w400 ">
				<span class="w95 inline_block tl"> <i class="i_ colorc mr10">*</i>原手机号码:
				</span> <span class=""> ${phoneEncryption} </span>
			</p>
			<p class="lh45  ">
				<span class="w95 inline_block tl"> <i class="i_ colorc mr10">*</i>手机验证码:
				</span> 
				<span class=""> 
					<input type="text" class="w220 pl10" id="validateCode" onblur="validateSmsCode('${phone}')">
					<i class="i_ ml20 c2980b9 an_yan_mobile_">
						<a onclick="sendSmsCode('${phone}')">获取验证码</a>
					</i>
					<i class="none i_ ml20 e5e9eb an_yan_mobile_s">25秒后重新获取</i>
				</span>
			</p>
			<div class="yanzheng_tis none" id="div_message">
				<p class="lh45  ">
					<span class="w95 inline_block tl"> </span> <span class="colorc">
						恭喜您，您的手机号码已经成功发送，请注意查收验证码。 </span>
				</p>
				<p class="lh45  ">
					<span class="w95 inline_block tl"> </span> <span
						class="lh18 inline_block"> 没收到短信？使用语音验证码进行手机验证。 <br />
						来电号码 010-52278080
					</span>
				</p>
				<p class="lh45 w360 ">
					<span class="w95 inline_block tl"> </span> 
					<span class="c2980b9" >
						<a onclick="sendScheduledSMS('${phone}')">获取语音验证码 </a>
					</span>
				</p>
			</div>

			<p class=" w360 mt10 ">
				<span class="w95 inline_block tl"> <i class="i_ colorc mr10">*</i>交易密码:
				</span> <span class=""> 
					<input type="password" id="tradePassword" class="w220 pl10">
					<br /> 
					<!-- <span class="input_tis ts_lh w215 mb5 block "
					style="margin-left: 98px;"> ts_re 是红色警告背景  
											ts_lh 是黄色警告背景
											ts_lv 是绿色正确提示背景  手机号码不正确！ 
					</span> -->
				</span>
			</p>
			<p class="kong45"></p>
		</div>
		<div class="kong35"></div>
		<a class="btn" onclick="nextStep('${phone}');">下一步</a>
	</div>
<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>
<script type="text/javascript">
	var Sms = {};
	//获取短信验证码
	function sendSmsCode(phone){
		//1 超过限制条数 2 发送成功
		var result = common.sendSmsCode(phone,"修改邮箱","MODIFY_MAILBOX");
		if("1" == result){
			layer.msg('验证码 超过限制条数!', {
				icon : 5
			});
		}else if("2" == result){
			//3、移除样式
			$("#div_message").removeClass("none")
			//2、追加样式
			//$("#div_message").addClass("ts_lh")
		}else{
			$("#div_message").removeClass("none")
		}
	}
	//验证短信验证码
	function validateSmsCode(phone){
		var validateCode = $("#validateCode").val();
		if(!validateCode || validateCode==""){
			return;
		}
		//1 验证码已过期 2 验证码错误 3 验证通过
		var result = common.validateSmsCode(phone,validateCode);
		if("1" == result){
			layer.msg('验证码已过期!', {
				icon : 5
			});
		}else if("2" == result){
			layer.msg('验证码错误!', {
				icon : 5
			});
		}else if("3" == result){
			$("#div_message").addClass("none");
			Sms.result = true;
		}
	}
	//获取语音验证码
	function sendScheduledSMS(phone){
		common.sendScheduledSMS(phone);
	}
	//下一步		
	function nextStep(phone){
		if(Sms.result){
			var tradePassword = $("#tradePassword").val();
			if(!tradePassword || tradePassword==""){
				layer.msg('交易密码不能为空!', {
					icon : 5
				});
				return;
			}
			var oldPwd = rsa.encode64(tradePassword);//加密后的为空的话值等于AA==
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/securityCenter/bankController/searcherTradingPwd.shtml",
		    	data:{"oldPwd":oldPwd},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		if(data.flag){
			    			//跳转到跟换邮箱选择页面
			    			window.location.href = BASE_PATH+"login/userController/toSsafetyNewMobilePage.shtml";
			    		}else{
			    			layer.msg('交易密码错误! 请您重新输入', {
								icon : 5
							});
							return;
			    		}
			    	}
			    }
			});
		}else{
			layer.msg('验证码错误！', {
				icon : 5
			});
		}
	}
</script>
</div>