var login = {
		
		// 打开登录窗口
		openLoginWin:function(){
			window.location.href=BASE_PATH+'login/loginController/toLogin.shtml'; 
		},
		// 登录
		loginSubmit:function(){
			
			$("#loginMes").css('display','none');
			var result = true;
			// 用户名不能为空提示
			if(null == $("#loginName").val() || '' == $("#loginName").val()){
				$("#loginNameMes").css('display','block');
				result = false;
			}else{
				$("#loginNameMes").css('display','none');
			}
			
			// 密码不能为空提示
			if(null == $("#password").val() || '' == $("#password").val()){
				$("#passwordMes").css('display','block');
				result = false;
			}else{
				$("#passwordMes").css('display','none');
			}
			
			
			// 展示了验证码，则需要验证验证码是否正确
			var viewKaptcha = $("#viewKaptcha").val();
			if(viewKaptcha!=null && viewKaptcha!=''){
				var kaptcha = $("#kaptcha").val();
				// 图形验证码不能为空提示
				if(null == kaptcha || ''==kaptcha ){
					$("#kaptchaMes").css('display','block');
					$("#kaptchaMes").html("图形验证码不能为空！");
					result = false;
				}else{
					$("#kaptchaMes").css('display','none');
				}
				
				if(result){
					// 验证验证注册码是否正确
					var kaptchaResult = common.validateKaptcha();
					
					if(!kaptchaResult){
						$("#kaptchaMes").css('display','block');
						$("#kaptchaMes").html("图形验证码不正确！");
						common.changeVerifyCode();
						return false;
					}else{
						$("#loginMes").css('display','none');
					}
				}
				
			}
			
			if(result){
				
				var isSaveCookie = $("#isSaveCookie").attr("val");
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+'login/loginController/login.shtml',
			    	data:{"loginName":rsa.encode64($("#loginName").val()),"password":rsa.encode64($("#password").val()),"isSaveCookie":isSaveCookie},
					dataType: "json",
					async:false, 
				    success: function(data){
				    	if(data.message.status ==200){
				    		var result = data.result;
				    		if(!data.result){
				    			// 登陆异常
				    			if(data.errorType == 1){
					    			$("#loginMes").css('display','block');
									$("#loginMes").html("登录名不存在!");
					    		}else if (data.errorType == 2){
					    			$("#loginMes").css('display','block');
									$("#loginMes").html("密码错误!");
					    		}else if (data.errorType == 3){
					    			$("#loginMes").css('display','block');
									$("#loginMes").html("用户已被锁定!");
					    		}else if (data.errorType == 5){
					    			$("#loginMes").css('display','block');
									$("#loginMes").html("该用户已被禁用，请联系客服！");
					    		}else if (data.errorType == 6){
					    			$("#loginMes").css('display','block');
									$("#loginMes").html("该用户已被禁用，请联系客服！");
					    		}
				    		}else{
				    			// 登陆成功
				    			common.toIndexPage(); 
				    		}
				    		if(undefined !=data.isViewKaptcha ){
				    			$("#isViewKaptcha").css('display','block');
				    			$("#viewKaptcha").val(data.isViewKaptcha);
				    		}
				    	}else{
				    		layer.msg('登录失败啦!', {icon: 5});
				    	}
					}
				});
			}
		},
		loginout:function(){
			window.location.href=BASE_PATH+"login/loginController/loginout.shtml"; 
		},
		init:function(){
			//勾选 效果调用,提交父容器参数 和全部勾选按钮参数
			escfutil.gougou(".login_i",".gou_s_js");
		    //底部背景图片居中
		    
		    //radio 单选效果调用,提交父容器参数
			escfutil.radios_(".select_js",".list_a_p1");
		    
		    $(".select_js .radio_s").click(function(){
		    	var eq =$(this).index(".select_js .radio_s");
		    	$(".select_c").removeClass("c2980b9").eq(eq).addClass("c2980b9");
		    })
		},
		enter:function(){
			$("#loginName").keydown(function(event){
				 if(event.which == 13){
					 $("#password").focus();
				 }
			})
			$("#password").keydown(function(event){
				 if(event.which == 13){
					 login.loginSubmit();
				 }
			})
		}
}