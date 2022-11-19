/**
 * 忘记密码
 */
var resetPwd = {
		smsTimeTemp:0,
		smsTime:0,
		sendEmailTemp:0,
		reg: /^-?[1-9]\d*$/,
		init:function(){
			//radio 单选效果调用,提交父容器参数
			escfutil.radios_login(".select_js",".list_a_p1");
		    
		    $(".select_js .radio_s").click(function(){
		    	var eq =$(this).index(".select_js .radio_s");
		    	$(".select_c").removeClass("c2980b9").eq(eq).addClass("c2980b9");
		    })
		},
		stepTwoInit:function(){
			// 获取系统参数中配置的 发送短信间隔时间
			var smsTime = common.getParamValueByKey("SEND_SMS_TIME");
			resetPwd.smsTime = smsTime;
			resetPwd.sendSmsCode();
		},
		// 重置密码下一步 
		nextStepOne:function(step){
			// 找回密码第一步
			var result = true;
			// 登录名为空判断
			var loginName =$("#loginName").val();
			if(loginName =='' || loginName == null){
				$("#loginNameMes").css('display','block');
				$("#loginNameMes").html("登录名不能为空！");
				result = false;
			}else{
				$("#loginNameMes").css('display','none');
			}

			// 验证码为空判断
			var kaptcha = $("#kaptcha").val();
			if(kaptcha =='' || kaptcha == null){
				$("#kaptchaMes").css('display','block');
				$("#kaptchaMes").html("图形验证码不能为空！");
				result = false;
			}else{
				$("#kaptchaMes").css('display','none');
			}
			
			if(result){
				// 验证登录名是否存在
				if(!user.validateLoginName()){
					$("#loginNameMes").css('display','block');
					$("#loginNameMes").html("登录名不存在！");
					return false;
				}else{
					$("#loginNameMes").css('display','none');
				}
				
				// 验证验证码是否是否正确
				if(!common.validateKaptcha()){
					$("#kaptchaMes").css('display','block');
					$("#kaptchaMes").html("图形验证码不正确！");
					common.changeVerifyCode();
					return false;
				}else{
					$("#kaptchaMes").css('display','none');
				}
				
				// 根据选择类型，加载页面
				var retrieveType = $(".c2980b9").prev().attr("val");
				resetPwd.flag =$("#flag_").val();//flag=1的时候是忘记交易密码 flag为空时是忘记登录密码
				if(retrieveType == 1){
					// 根据手机号码找回
					$("#retrievePage").load(BASE_PATH+"login/userController/toPhoneRetrievePage.shtml",{"loginName":$("#loginName").val(),"flag":resetPwd.flag});
				}else{
					// 邮箱找回
					// 验证用户是否绑定邮箱
					if(!resetPwd.bingEmail(loginName)){
						$("#loginNameMes").css('display','block');
						$("#loginNameMes").html("用户暂未绑定邮箱，请选择其它方式！");
					}else{
						$("#loginNameMes").css('display','none');
						//调用发送邮件接口
						resetPwd.sendEmail(resetPwd.flag);
					}
				}
			}
		},
		nextStepTwo:function (){
			// 找回密码第二步
			// 验证手机验证码是否正确
			var loginName = $("#loginName").val();
			var smsCode = $("#smsCode").val();
			if(null == smsCode || "" == smsCode){
				$("#smsCodeMes").css('display','block');
				$("#smsCodeMes").html("手机验证码不能为空！");
				return false;
			}else{
				$("#smsCodeMes").css('display','none');
			}
			
			// 1 验证码已过期 2 验证码错误 3 验证通过
			var result = common.validateSmsCode(loginName,smsCode);
			if(result == 1){
				$("#smsCodeMes").css('display','block');
				$("#smsCodeMes").html("手机验证码已过期，请重新获取！");
			}else if (result == 2){
				$("#smsCodeMes").css('display','block');
				$("#smsCodeMes").html("手机验证码不正确！");
			}else if (result == 3){
				var flag = $("#flags").val();
				//flag=1的是跳到重置交易密码页面
				if(flag==1){
					// 加载重置密码页面
					$("#retrievePage").load(BASE_PATH+"login/userController/toResetTradPwdPage.shtml",{"loginName":loginName});
				}else{
					// 加载重置密码页面
					$("#retrievePage").load(BASE_PATH+"login/userController/toResetPwdPage.shtml",{"loginName":loginName});
				}
			}
		},
		//重置登录密码
		resetPwd:function(){
			var newPassword = $("#newPassword").val();
			var newPasswordTo = $("#newPasswordTo").val();
			var result = true;
			if(null == newPassword || '' == newPassword){
				$("#newPasswordMes").css('display','block');
				$("#newPasswordMes").html("新密码不能为空！");
				result = false;
			}else{
				$("#newPasswordMes").css('display','none');
			}
			if(null == newPasswordTo || '' == newPasswordTo){
				$("#newPasswordToMes").css('display','block');
				$("#newPasswordToMes").html("确认新密码不能为空！");
				result = false;
			}else{
				$("#newPasswordToMes").css('display','none');
			}
			
			if(result){
				// 验证两次密码是否一致
				if(newPassword!=newPasswordTo){
					$("#newPasswordMes").css('display','block');
					$("#newPasswordMes").html("新登陆密码与确认登陆密码不一致！");
				}else{
					// 验证密码复杂度是否达标 （6-20位字符，至少包含数字，大写字母，小写字母，符号中的两种）
					if(!otherinfoCommon.pwdReg.test(newPassword)){
						$("#newPasswordMes").css('display','block');
						$("#newPasswordMes").html("密码格式不正确！（登录密码只能为 6 - 32 位，至少包含两种字符且不能与用户名一样）");
						return false;
					}
					
					var loginName = $("#loginName").val();
					$.ajax({
						type: "POST",
				    	url : BASE_PATH+"/login/userController/resetPwd.shtml",
				    	data:{"loginName":rsa.encode64(loginName),"newPassword":rsa.encode64(newPassword)},
						dataType: "json",
						async:false, 
					    success: function(data){
					    	if(data.message.status ==200){
					    		layer.msg('密码重置成功，系统稍后会自动返回到登陆页面！', {icon: 1});
					    		setTimeout("common.toLoginPage()",3000); 
					    	}else{
					    		layer.msg('密码重置失败,请联系管理员!', {icon: 5});
					    	}
						}
					});
				}
			}
		},
		
		//重置交易密码
		resetTradPwd:function(){
			var newPassword = rsa.encode64($("#newPassword").val());
			var newPasswordTo = rsa.encode64($("#newPasswordTo").val());
			var str = $("#newPasswordTo").val();
			var temp=true;
			if(newPassword=='AA=='){//加密后如果为空的话值是AA==
				$("#newPassword_s").css('display','block');
				$("#newPassword_s").html("新密码不能为空!");
				temp=false;
			}
			else if(newPasswordTo =='AA=='){
				$("#newPasswordTo_s").css('display','block');
				$("#newPasswordTo_s").html("确认新密码不能为空!");
				temp=false;
			}
//			// 验证两次密码是否一致
			else if(newPassword!=newPasswordTo){
				$("#newPasswordTo_s").css('display','block');
				$("#newPasswordTo_s").html("两次输入的密码不一致!");
				$("#newPassword_s").css('display','none');
				temp=false;
			}
			else if(!resetPwd.reg.test(newPasswordTo) && str.length !=6){
				$("#newPasswordTo_s").css('display','block');
				$("#newPasswordTo_s").html("交易密码只能是6位数字!");
				$("#newPassword_s").css('display','none');
				temp=false;
			}
			if(temp){
				var loginName = $("#loginName").val();
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"/login/userController/resetTradPwd.shtml",
			    	data:{"loginName":rsa.encode64(loginName),"newPassword":newPassword},
					dataType: "json",
					async:false, 
				    success: function(data){
				    	if(data.message.status ==200){
				    		layer.msg('交易密码重置成功', {icon: 1});
				    		window.location.href = BASE_PATH + "securityCenter/bankController/personalData.shtml";
//				    		setTimeout("common.toLoginPage()",3000); 
				    	}else{
				    		layer.msg('交易密码重置失败,请联系管理员!', {icon: 5});
				    	}
					}
				});
			}
			},
		
		// 等待多时秒后发送短信
		sendSmsTime:function (){
			if(resetPwd.smsTimeTemp == 0){  
		    	 $('#sendSmsCodeBu').bind('click', resetPwd.sendSmsCode);
		    	 $('#sendSmsCodeBu').html("发送验证码");  
		    	 $('#sendSmsCodeBu').removeClass("c798383"); 
		    	 $('#sendSmsCodeBu').addClass("c2980b9"); 
		    }else{
		    	  $('#sendSmsCodeBu').html(resetPwd.smsTimeTemp--+" 秒后发送验证码");  
				  setTimeout("resetPwd.sendSmsTime()",1000); 
		    }
		},
		// 发送验证码短信
		sendSmsCode:function(){
			resetPwd.smsTimeTemp = resetPwd.smsTime;
			var phoneNo = $("#phoneNo").val();
			resetPwd.flag = $("#flags").val();
			var result = '';
			if(resetPwd.flag ==1){
				result = common.sendSmsCode(phoneNo,"找回交易密码","REST_TRADPWD_CODE");
			}else{
				result = common.sendSmsCode(phoneNo,"找回密码","REST_CODE");
			}
				
			if(result == 1){
				$("#smsCodeMes").css('display','block');
				$("#smsCodeMes").html("该手机获取的验证码超过指定次数，请明日再试！");
				$("#resetPwdNext").removeClass("btn");
				$("#resetPwdNext").addClass("btn_h");
			}else{
		    	resetPwd.sendSmsTime();
			}
			$('#resetPwdNext').bind('click', resetPwd.nextStepTwo);
			$("#sendSmsCodeBu").unbind("click")
			$('#sendSmsCodeBu').removeClass("c2980b9"); 
	    	$('#sendSmsCodeBu').addClass("c798383"); 
		},
		//  等待多时秒后发送邮件
		sendEmailTime:function(){
			if(resetPwd.sendEmailTemp == 0){  
		    	 $('#sendEmailBu').bind('click', function(){resetPwd.sendEmail(resetPwd.flag)});
		    	 $('#sendEmailBu').html("重新发送");  
		    	 $('#sendEmailBu').removeClass("c798383"); 
		    	 $('#sendEmailBu').addClass("c2980b9"); 
		    }else{
		    	var result = resetPwd.sendEmailTemp--;
		    	  $('#sendEmailBu').html("重新发送（"+result+"）");  
				  setTimeout("resetPwd.sendEmailTime()",1000); 
		    }
		},
		// 发送邮件
		sendEmail:function(pwdType){
			resetPwd.sendEmailTemp = resetPwd.smsTime;
			var loginName = $("#loginName").val();
			var result = false;
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/login/userController/sendEmail.shtml",
		    	data:{"loginName":loginName,"pwdType":pwdType},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		// 加载邮件发送成功页面
						$("#retrievePage").load(BASE_PATH+"login/userController/toEmailRetrievePage.shtml",
								{"loginName":$("#loginName").val(),"email":data.email,"flag":pwdType});
			    	}else{
			    		layer.msg('发送邮件失败,请联系管理员!', {icon: 5});
			    	}
				}
			});
			return result;
		},
		// 是否绑定邮箱
		bingEmail:function(loginName){
			var result = false;
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/login/userController/bingEmail.shtml",
		    	data:{"loginName":loginName},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		result = data.result;
			    	}else{
			    		layer.msg('验证是否绑定邮箱失败,请联系管理员!', {icon: 5});
			    	}
				}
			});
			return result;
		},
		emailInit:function(){
			// 获取系统参数中配置的 发送短信间隔时间
			resetPwd.smsTime = common.getParamValueByKey("SEND_SMS_TIME");
			resetPwd.sendEmailTemp = resetPwd.smsTime;
			resetPwd.sendEmailTime();
			$("#sendEmailBu").unbind("click")
			$('#sendEmailBu').removeClass("c2980b9"); 
			$('#sendEmailBu').addClass("c798383"); 
		},
		
		// 发送语音验证码短信
		sendScheduledSMS:function(){
			var phoneNo = $("#phoneNo_").val();
			var result = common.sendScheduledSMS(phoneNo);
			if(result == 2){
				layer.msg('语音验证码已发送成功，请留意!', {icon: 5});
			}else{
				layer.msg('验证码已过期，请重新获取!', {icon: 5});
			}
		}
}