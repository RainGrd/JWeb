/**
 *  忘记登录密码
 */
//radio 单选效果调用,提交父容器参数
		    radios_(".select_js",".list_a_p1");
		    $(".select_js .radio_s").click(function(){
		    	var eq =$(this).index(".select_js .radio_s");
		    	$(".select_c").removeClass("c2980b9").eq(eq).addClass("c2980b9");
		    })
		    /*单选效果*/
			function radios_(a,b){
		    	$(a +" .radio_s").eq(0).addClass("c2980b9").html('<img src="../images/radio_x.png" class="block" />').attr("val","1");
				    $(a +" .radio_s").click(function(){
					$(a +" "+b).removeClass("c2980b9");
					$(a +" "+b).eq($(this).index(a +" .radio_s")).addClass("c2980b9");
					$(a +" .radio_s").html("");
					$(a +" .radio_s").attr("val","0");
					$(this).html('<img src="/ep2p/theme/default/images/radio_x.png" class="block" />');
					$(this).attr("val","1");
				})
		    }
var forgerLoginPwd={
		init:function(){
		},
		
		//下一步事件
		next:function(step){
			//第一步验证
			if(step==1){
				//如果用户名存在 
				if(!forgerLoginPwd.validateUserName()){
					
				}else{
				}
				// 验证验证码是否是否正确
				if(!common.validateKaptcha()){
					layer.msg('图形验证码不正确！', {icon: 5});
					common.changeVerifyCode();
					return false;
				}
				// 根据选择类型，加载页面
				var retrieveType = $(".c2980b9").prev().attr("val");
				if(retrieveType == 1){
					// 根据手机号码找回
					$("#retrievePage").load(BASE_PATH+"login/userController/toPhoneRetrievePage.shtml",{"loginName":$("#userName").val()});
//				}else{
					// 邮箱找回
//					alert("根据邮箱找回");
//				}
				}
			}else{
				// 找回密码第二步
				// 验证手机验证码是否正确
				var loginName = $("#userName").val();
				var smsCode = $("#smsCode").val();
				if(null == smsCode || "" == smsCode){
					layer.alert("手机验证码不能为空！", {icon: 5});
					return ;
				}else{
				}
				
				// 1 验证码已过期 2 验证码错误 3 验证通过
				var result = common.validateSmsCode(loginName,smsCode);
				if(result == 1){
					layer.alert("手机验证码已过期，请重新获取！", {icon: 5});
				}else if (result == 2){
					layer.alert("手机验证码不正确！", {icon: 5});
				}else if (result == 3){
					// 加载重置密码页面
					$("#retrievePage").load(BASE_PATH+"login/userController/toResetPwdPage.shtml",{"loginName":loginName});
				}
			}
		},
		
		// 验证用户名是否存在
		validateUserName:function(){
			var userName = $("#userName").val();
			var result = false;
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"securityCenter/bankController/validateUserName.shtml?userName="+userName,
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
			return result;
		}
		
}