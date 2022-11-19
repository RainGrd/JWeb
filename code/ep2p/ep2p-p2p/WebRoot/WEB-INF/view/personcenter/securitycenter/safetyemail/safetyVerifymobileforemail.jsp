<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>手机号更换安全邮箱页面 -->
<div class="sidebar_b borrow_div fl">
	<!--  更换安全手机 s-->
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全邮箱</span>
		</div>
		<h4>验证手机</h4>
		<div class=" hui_l borderNone size14 colorDarkBlue">
			<p class="lh45 ">
				<span class="w49_ inline_block tr mr20"> <i
					class="i_ colorc mr10"></i>已绑定手机
				</span> <span class=""> ${phoneEncryption} </span>
			</p>
			<p class="lh45  ">
				<span class="w49_ inline_block tr mr20"> <i
					class="i_ colorc mr10">*</i>手机验证码
				</span> 
				<span class=""> 
					<input type="text" class="w100 pl10" id="validateCode" onblur="validateSmsCode('${phone}')">
					<i class="i_ ml20 c2980b9 an_yan_mobile_">
						<a onclick="sendSmsCode('${phone}')">获取验证码</a>
					</i>
					<i class="none i_ ml20 d2d2d2 an_yan_mobile_s">25秒后重新获取</i>
				</span>
			</p>
			<div class="yanzheng_tis none" id="div_message">
				<p class="lh45  ">
					<span class="w52_ inline_block tl"> </span> <span class="colorc">
						恭喜您，您的验证码已经成功发送，请注意查收。 </span>
				</p>
				<p class="lh45  ">
					<span class="w52_ inline_block tl"> </span> <span
						class="lh22 inline_block d2d2d2"> 没收到短信？使用语音验证码进行手机验证。 <br />
						来电号码 010-52278080<a onclick="sendScheduledSMS('${phone}')" class="btn_samllss">获取语音验证码</a>
					</span>
				</p>
			</div>

			<p class=" mt10 ">
				<span class="w49_ inline_block tr mr20"> <i
					class="i_ colorc mr10">*</i>交易密码
				</span> 
				<span class=""> 
					<input type="password" id="tradePassword" class="w220 pl10">
					<br /> 
				</span>
			</p>
		</div>
		<div class="kong35"></div>
		<a class="btn w300 m_auto displayB" onclick="nextStep();">下一步</a>
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
		function nextStep(){
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
				    			parent.window.location.href = BASE_PATH+"login/userController/openSmsUpdateEmail.shtml?query="+oldPwd;
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