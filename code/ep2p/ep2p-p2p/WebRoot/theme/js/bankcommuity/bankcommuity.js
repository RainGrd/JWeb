/**
 * 理财社区js
 */
var bankcommuity = {
		// 打开理财社区
		openBankCommuity:function(){
			// 验证用户是否登录，是否设置用户名
			// 验证手机号码是否已经存在
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"forum/forumController/validate.shtml",
		    	data:{},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		if(data.isLogin == 'no'){
			    			// 用户未登录
			    			window.open(BASE_PATH+'forum/forumController/toForum.shtml?isLogin=no'); //iframe的url
			    		}else if (data.isSetName == 'no'){
			    			// 未设置账号
			    			layer.msg('账号未设置啦!', {icon: 5});
			    		}else{
			    			// 用户已设置账号并且已经登录
			    			window.open(BASE_PATH+'forum/forumController/toForum.shtml?isLogin=yes'); //iframe的url
			    		}
			    	}else{
			    		layer.msg('验证失败啦!', {icon: 5});
			    	}
				}
			});
		}
		
}