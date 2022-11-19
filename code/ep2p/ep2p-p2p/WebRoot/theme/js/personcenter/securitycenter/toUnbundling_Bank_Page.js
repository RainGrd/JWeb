/**
 * 个人中心_我的银行卡JS类
 */
var unbun_Bank = {
	//弹窗确定事件
	sure:function(){
		 var flag=true;
		 var pid = $("#bank_pid").val();
		 var tradPwd = rsa.encode64($("#tradPassWord").val());
		 if(tradPwd =='AA==' || tradPwd==null){
			 layer.msg('交易密码不能为空!', {icon: 5});
			 flag=false;
		 }else{
			var i = layer.load(0,{time: 10*1000}); //并且设定最长等待10秒 
			$.ajax({
				type : "POST",
				async : false,
				url : BASE_PATH+"userinfo/centerController/validataTradPwd.shtml",
			    data:{"tradPwd":tradPwd},
				dataType : "json",
				success : function(data) {
					if (data.message.status == 200) {
						//如果密码正确就解绑银行卡
						$.ajax({
							type: "POST",
					    	url : BASE_PATH+"/securityCenter/bankController/selectBankTradingPwdByBankCar.shtml",
					    	data:{"pid":pid,"passWord":tradPwd},
							dataType: "json",
							async:false, 
						    success: function(data){
						    	if(data.message.status ==200){
						    		//刷新父页面
						    		parent.location.reload()
						    	}else{
						    		layer.msg('解绑银行卡失败,请联系管理员!', {icon: 5});
						    	}
							}
						});
						
					} else {
						if(data.errorCode == 'tradePwdError'){
							layer.close(i);
							$("#tradPwdError").text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
							$("#tradPwdError").show();
							flag=false;
						}else if(data.errorCode == 'tradePwdBank'){
							layer.close(i);
							$("#tradPwdError").html(USER_PWD_MSG.TO_SET_TRADE_PWD);
							$("#tradPwdError").show();
							flag=false;
						}else if(data.errorCode == 'tradeFreeze'){
							layer.close(i);
							$("#tradPwdError").text("交易密码超过上限，为保护账户安全，系统禁止交易"+data.t+"分钟");
							$("#tradPwdError").show();
							flag=false;
						}
					}
				}
			});
			}
//		 if(flag){
//			 
//			 parent.location.reload()
//		 }
	},
	cancel:function(){
		//获取当前打开的窗口
		var index = parent.layer.getFrameIndex(window.name);
		//关闭当前窗口
		parent.layer.close(index);
	},
	// 加载数据
	loadData:function(){
		var url = BASE_PATH + "securityCenter/bankController/selectBankListByCusId.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{data:""},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		parent.myBank.loadData();
	    			//关闭当前窗口
	    			parent.layer.close(parent.layer.getFrameIndex(window.name));
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	//我的银行卡信息
	myBankInfo:function(data){
		var fId = "";
		if(null != data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var bankcardNo = data[i].bankcardNo;
				fId +='<div>' +
					     '<ul>' +
							'<li>' +data[i].belongingBank+'</li>'+
							'<li>尾号' +data[i].bankcardNo+'储蓄卡</li>'+
					 	'</ul>'+
							'<a href="javascript:myBank.unbundling('+"'"+bankcardNo+"'"+');">解绑'+'</a>'+
					'</div>' +'<br/>';
			}
		}
		$("#bank_01").html(fId);
	},
	
}