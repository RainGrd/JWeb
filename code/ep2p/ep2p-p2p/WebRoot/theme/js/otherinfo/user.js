/**
 * 用户js类
 */
var user = {
		registeredInit:function(){
			$("#sub").hide();
			$("#complete").hide();
		},
		// 打开注册窗口
		openUserRegisteredWin:function(){
			window.open(BASE_PATH+'login/userController/toRegistered.shtml'); //iframe的url
		},
		// 发送手机验证码
		sendPhone:function(){
			
		},
		// 发送语音验证码
		sendVoice:function(){
			
		},// 验证登录名是否存在
		validateLoginName:function(){
			var loginName = $("#loginName").val();
			var result = false;
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"login/userController/validateLoginName.shtml?loginName="+loginName,
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
