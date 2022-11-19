/**
 * 用户注册js类
 */
var registered = {
		// 注册成功后，等待remainTime秒跳转到首页
		time:3,
		smsTime:0,
		smsTimeTemp:0,
		init:function(){
			escfutil.gougou(".reg_all");
//			$(".reg_js_a").click(function(){
//				$(".reg_all").addClass("none").eq(1).removeClass("none");
//			})
//			$(".reg_js_b").click(function(){
//				$(".reg_all").addClass("none").eq(2).removeClass("none");
//			})
		},
		// 密码跟踪
		valiPwd:function(){
			var pwd = $("#password").val();
			if(pwd.length>0){
				$("#pwdLength").removeClass("reg_an_b").addClass("reg_an_a");
			}else{
				$("#pwdLength").removeClass("reg_an_a").addClass("reg_an_b");
			}
			
			if(otherinfoCommon.pwdReg.test(pwd)){
				$("#pwdStr").removeClass("reg_an_b").addClass("reg_an_a");
			}else{
				$("#pwdStr").removeClass("reg_an_a").addClass("reg_an_b");
			}
		},
		// 点击下一步
		next:function(){
			// 填写内容验证
			if(!registered.validate()){
				return ;
			}
			// 给第二步手机号码赋值
			var phoneNo = $("#phoneNo").val();
			$("#phoneStr").html(common.phoneNoEncryption(phoneNo));
			// 获取系统参数中配置的 发送短信间隔时间
			var smsTime = common.getParamValueByKey("SEND_SMS_TIME");
			registered.smsTime = smsTime;
			
			// 发送验证码
			registered.sendSmsCode();
			// div 隐藏
			$("#stepOne").addClass("none");
			$("#stepTwo").removeClass("none");
			
		},
		// 用户提交注册
		submit:function(){
			// 验证手机验证码or语音验证码
			var phoneNo = $("#phoneNo").val();
			var smsCode = $("#smsCode").val();
			
			if(null == smsCode || "" == smsCode){
				$("#stepTwoMessage").html("手机验证码不能为空！");
				return ;
			}else{
				$("#stepTwoMessage").html("");
			}
			
			// 1 验证码已过期 2 验证码错误 3 验证通过
			var result = common.validateSmsCode(phoneNo,smsCode);
			if(result == 1){
				$("#stepTwoMessage").html("验证码已过期，请重新获取！");
			}else if (result == 2){
				$("#stepTwoMessage").html("手机验证码不正确！");
			}else if (result == 3){
				var refereeUser =  $("#referralUser").val();	// 推介码
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"login/userController/registeredSubmit.shtml",
			    	data:{"phoneNo":rsa.encode64($("#phoneNo").val()),"refereeUser":refereeUser,"password":rsa.encode64($("#password").val()+"")},
					dataType: "json",
					async:false, 
				    success: function(data){
				    	if(data.message.status ==200){
				    		// div 隐藏
				    		$("#stepTwo").addClass("none");
							$("#complete").removeClass("none");
							registered.remainTime();
				    	}else{
				    		layer.msg('注册失败啦!', {icon: 5});
				    	}
					}
				});
			}
		},
		// 用户注册验证
		validate:function(){
			var phoneNo = $("#phoneNo").val();
			if(null == phoneNo || ''==phoneNo){
				$("#message").html("登陆手机号不能为空！");
				return false;
			}else{
				$("#message").html("");
			}
			// 验证手机号码格式是否正确
			if(!otherinfoCommon.mobileReg.test(phoneNo)){
				$("#message").html("手机格式不正确！（号码开头为13,14,15,17,18，长度11位   ）！");
				return false;
			}else{
				$("#message").html("");
			}
			
			var result = true;
			// 验证手机号码是否已经存在
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"login/userController/registeredValidate.shtml",
		    	data:{"phoneNo":phoneNo},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		result = data.result;
			    	}else{
			    		layer.msg('验证手机失败,请联系管理员!', {icon: 5});
			    	}
				}
			});
			
			if(!result){
				$("#message").html("该手机号码已被注册，请换一个手机号码注册！");
				return false;
			}else{
				$("#message").html("");
			}
			
			var pwd =  $("#password").val();
			if(null == pwd || ''==pwd){
				$("#message").html("登陆密码不能为空！")
				return false;
			}else{
				$("#message").html("");
			}
			
			// 验证密码复杂度是否达标 （6-20位字符，至少包含数字，大写字母，小写字母，符号中的两种）
			if(!otherinfoCommon.pwdReg.test(pwd)){
				$("#message").html("密码格式不正确！");
				layer.alert("密码格式为：6-32位字符，至少包含数字，大写字母，小写字母，符号中的两种", {icon: 5});
				return false;
			}else{
				$("#message").html("");
			}
			
			var kaptcha = $("#kaptcha").val();
			if(null == kaptcha || ''==kaptcha ){
				$("#message").html("图形验证码不能为空！")
				return false;
			}else{
				$("#message").html("");
			}
			
			// 验证验证注册码是否正确
			var result = common.validateKaptcha();
			
			if(!result){
				$("#message").html("图形验证码不正确！");
				common.changeVerifyCode();
				return false;
			}else{
				$("#message").html("");
			}
			var referralCode = $("#referralCode").val();
			if(null!=referralCode && referralCode.length > 0){
				// 验证推介码是否存在
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"login/userController/getByReferralCode.shtml",
			    	data:{"referralCode":referralCode},
					dataType: "json",
					async:false, 
				    success: function(data){
				    	if(data.message.status ==200){
				    		if(null == data.result || '' == data.result){
				    			result = false;
				    		}else{
				    			 $("#referralUser").val(data.result.pid);
				    		}
				    		result = data.result;
				    	}else{
				    		layer.msg('验证推介码是否存在失败,请联系管理员!', {icon: 5});
				    	}
					}
				});
				
				if(!result){
					$("#message").html("推介码不存在！");
					return false;
				}else{
					$("#message").html("");
				}
			}
			
			var agreement = $("#agreement").attr("val");
			if("0" == agreement){
				$("#message").html("请同意e生财富注册服务协议！");
				return false;
			}else{
				$("#message").html("");
			}
			return true;
		},
		remainTime :function (){  
		    if(registered.time==0){  
		    	common.toIndexPage();
		    }  
		    $('#endtime').html(registered.time--);  
		    setTimeout("registered.remainTime()",1000); 
		},
		// 等待多时秒后发送短信
		sendSmsTime:function (){
			if(registered.smsTimeTemp == 0){  
		    	 $('#sendSmsCodeBu').bind('click', registered.sendSmsCode);
		    	 $('#sendSmsCodeBu').html("发送验证码");  
		    	 $('#sendSmsCodeBu').removeClass("c798383"); 
		    	 $('#sendSmsCodeBu').addClass("c2980b9"); 
		    }else{
		    	  $('#sendSmsCodeBu').html(registered.smsTimeTemp--+" 秒后发送验证码");  
				  setTimeout("registered.sendSmsTime()",1000); 
		    }
		},
		// 发送验证码短信
		sendSmsCode:function(){
			registered.smsTimeTemp = registered.smsTime;
			var phoneNo = $("#phoneNo").val();
			var result = common.sendSmsCode(phoneNo,"用户注册","REGISTERED_CODE");
//			var result = 2; 
			if(result == 1){
				$("#stepTwoMessage").html("该手机获取的注册验证码超过指定次数，请明日再试！");
			}else{
				$("#stepTwoMessage").html("");
				$("#sendSmsCodeBu").unbind("click")
				$('#sendSmsCodeBu').removeClass("c2980b9"); 
		    	$('#sendSmsCodeBu').addClass("c798383"); 
				registered.sendSmsTime();
			}
		},
		// 发送语音验证码短信
		sendScheduledSMS:function(){
			var phoneNo = $("#phoneNo").val();
			var result = common.sendScheduledSMS(phoneNo);
			if(result == 2){
				layer.msg('语音验证码已发送成功，请留意!', {icon: 5});
			}else{
				layer.msg('验证码已过期，请重新获取!', {icon: 5});
			}
		}
}