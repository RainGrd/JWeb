/**
 * 个人中心-vip信息
 */
var parm="";
	 /*单选效果*/
	//a是父容器，b是子容器
	 function radios_(a,b){
	 	    $(a +" .radio_s").click(function(){
	 		$(a +" "+b).removeClass("c2980b9");
	 		$(a +" "+b).eq($(this).index(a +" .radio_s")).addClass("c2980b9");
	 		$(a +" .radio_s").html("");
	 		$(".ceshi_q").html("￥"+$(this).attr("value")*10);
	 		parm = $(this).attr("value");
	 		$(a +" .radio_s").attr("val","0");
	 		$(this).html('<img src="/ep2p/theme/default/images/radio_x.png" class="block" />');
	 		$(this).attr("val","1");
	 	})
	 }
var shoppingVip = {
		initfunc:function(){
		   
		},
		// 加载数据
		loadData:function(){
			var url = BASE_PATH+"userinfo/centerController/getUserInfoByPid.shtml"
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{data:""},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		shoppingVip.vipInfo(data.data);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		},
	    
		//填充vip信息
		vipInfo:function(data){
			var custNameOrCustAccount=data.customerName;
			//如果用户名为空，就用手机号码
			if(data.customerName==null){
				custNameOrCustAccount=data.phoneNo;
			}
			$('.ktzh').html(custNameOrCustAccount);//开通账户
			if(data.totalAccount==null && data.totalAccount==""){
				$('.kyye').html('￥'+0);//可用余额
			}else{
				$('.kyye').html('￥'+data.totalAccount);//可用余额
			}
			
			
		},
		
		//确定按钮事件
		sure:function(){
			//购买的月份
			var month="";
			if(parm==""){
				month=12;
			}
    		else if(parm==24){
    			month=24;
			}else{
				month=36;
			}
//			var tradPwd = $("#tradPwd").val();
			 var tradPwd = rsa.encode64($("#tradPwd").val());
			var i = layer.load(0,{time: 10*1000}); //并且设定最长等待10秒 
			var amount = $(".ceshi_q").html();//扣钱
			var amounts = amount.substr(1,amount.length)
			$.ajax({
				type : "POST",
				async : false,
				url : BASE_PATH+"userinfo/centerController/validataTradPwd.shtml",
			    data:{"tradPwd":tradPwd},
				dataType : "json",
				success : function(data) {
					if (data.message.status == 200) {
						//1:更新vip时长 
			    		shoppingVip.updateCustVipTime(amounts,month);
					} else {
						if(data.errorCode == 'tradePwdError'){
							layer.close(i);
							$("#tradPwdError").text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
							$("#tradPwdError").show();
						}else if(data.errorCode == 'tradePwdBank'){
							layer.close(i);
							$("#tradPwdError").html(USER_PWD_MSG.TO_SET_TRADE_PWD);
							$("#tradPwdError").show();
						}else if(data.errorCode == 'tradeFreeze'){
							layer.close(i);
							$("#tradPwdError").text("交易密码超过上限，为保护账户安全，系统禁止交易"+data.t+"分钟");
							$("#tradPwdError").show();
						}
					}
				}
			});
		},
		
		//更新客户表的vip时长
		updateCustVipTime:function(amounts,month){
			 var url = BASE_PATH+"userinfo/centerController/updateCustVipTime.shtml"
				$.ajax({
					type: "POST",
			    	url : url,
			    	data:{"amount":amounts,"month":month},
					dataType: "json",
				    success: function(data){
				    	if(data.count>0){
				    		layer.msg('购买成功!', {icon: 5});
				    		//关闭当前窗口
//				    		parent.layer.close(parent.layer.getFrameIndex(window.name));
				    		parent.location.reload(false);
				    	}else{
				    		layer.msg('购买失败!', {icon: 5});
				    	}
					}
				});
		},
		
		//取消
		cancle:function(){
			//关闭当前窗口
			parent.layer.close(parent.layer.getFrameIndex(window.name));
		},
		
		//充值
		chongzhi:function(){
			window.top.location.href = BASE_PATH+'recharge/userRechargeController/toUserRecharge.shtml';
		},
};


//加载文件
(function(){
	radios_(".vip_g_lists",".vip_radioa");
	shoppingVip.loadData();
})();