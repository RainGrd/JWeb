/**
 * 个人中心-安全中心-修改登录密码
 */

var updateLoginPwd = {
		pwdReg:/(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,32}$/,
		initfunc:function(){
		},
		// 加载数据
		loadData:function(){
			var url = BASE_PATH + "securityCenter/bankController/selectPersonData.shtml";
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{data:""},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		safetyIndex.personData(data.data);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		},
		
		//查登录密码是否正确
		saveLoginPwd:function(){
			var oldPwd = rsa.encode64($("#oldPwd").val());//加密后的为空的话值等于AA==
			var new_pwd = rsa.encode64($("#newPwd").val());
			var sureNewPwd = rsa.encode64($("#sureNewPwd").val());
			var sureNewPwd1 = $("#sureNewPwd").val();
			
			if(oldPwd=='AA=='){
				$("#oldPwd_s").css('display','block');
				$("#oldPwd_s").html("原密码不能为空！");
			}else if(oldPwd !='AA=='){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"/securityCenter/bankController/searcherLoginPwd.shtml",
			    	data:{"oldPwd":oldPwd},
					dataType: "json",
					async:false, 
				    success: function(data){
				    	if(data.message.status ==200){
				    		if(data.flag){
				    			if(new_pwd=='AA=='){
				    				$("#newPwd_s").css('display','block');
				    				$("#newPwd_s").html("新密码不能为空！"); 
				    				$("#oldPwd_s").css("display","none");
				    			}else if(sureNewPwd=='AA=='){
				    				$("#sureNewPwd_s").css('display','block');
				    				$("#sureNewPwd_s").html("确认新密码不能为空！");
				    				$("#oldPwd_s").css('display','none');
				    				$("#newPwd_s").css('display','none');
				    			}else if(new_pwd != sureNewPwd){
				    				$("#sureNewPwd_s").css('display','block');
				    				$("#sureNewPwd_s").html("两次输入的密码不一致！");
				    				$("#oldPwd_s").css('display','none');
				    				$("#newPwd_s").css("display","none");
				    			}else if(!updateLoginPwd.pwdReg.test(sureNewPwd1)){
				    				$("#sureNewPwd_s").css('display','block');
				    				$("#sureNewPwd_s").html("登录密码只能为 6 - 32 位，至少包含一位字母且不能与用户名一样!");
				    			}else{
				    				//保存心的登录密码
				    				updateLoginPwd.save(sureNewPwd);
				    			}
				    		}else{
				    			$("#oldPwd_s").css('display','block');
								$("#oldPwd_s").html("原密码不正确！");
								$("#newPwd_s").css('display','none');
								$("#sureNewPwd_s").css('display','none');
								return;
				    		}
				    	}
				    }
				});
			}
		},
		
		//保存修改后的登录密码
		save:function(sureNewPwd){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/securityCenter/bankController/updateLoginPwdByCustId.shtml",
		    	data:{"sureNewPwd":sureNewPwd},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		if(data.count > 0){
//			    			layer.msg('密码修改成功!', {icon: 5});
			    			//关闭当前窗口
			    			parent.layer.close(parent.layer.getFrameIndex(window.name));
			    		}
			    	}
			    }
			});
		},
};


//加载文件
(function(){

})();