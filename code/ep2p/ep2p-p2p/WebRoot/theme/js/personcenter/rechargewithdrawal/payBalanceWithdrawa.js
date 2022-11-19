/**
 * 客户提现类
 */
var CustWithdrawalClass = {
	//提现银行卡列表
	htmlWithdrawaBankList : function(vos){
		var html = "";
		if(vos != null && vos.length>0){
			for(var i=0;i<vos.length;i++){
				var pid = vos[i].pid;
				var bankcardNo = vos[i].bankcardNo;
				html += '<span class="tis_yin_ping mr15 inline_block pos-r oh  xuan_zhong" pid="'+pid+'">'+
					'<img class="block yin_h_ka" alt="'+vos[i].belongingBank+'"/>'+
					'<p class="pos-a chi_ka size14">**** *** ****'+bankcardNo+'</p>'+
					'</span>'
			}
		}
		html +='<span id="check_bank_alert" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">请选择银行卡！</span>'
		$("#pay_balance_bank_div").html(html);
		//添加银行图片,给银行图片添加class ="yin_h_ka" ,自动判断 alt的值 然后自动填充图片
		var y_;
		var src_;
		for(var i=0;i<$(".yin_h_ka").length;i++){
			y_ = $(".yin_h_ka").eq(i).attr("alt");
			$(".yin_h_ka").eq(i).attr("src",'/ep2p/theme/default/images/bank'+f_(y_)+'.png');
		}
		/**
		 * 充值平台 选中效果
		 */
		$(".xuan_zhong").click(function(){
			$("#bank_id").val($(this).attr("pid"));
			$(".xuan_zhong").attr("style","");
			$(this).css("border","#2980b9 solid 1px");
		});
	},
	/**
	 * 短信发送间隔时间
	 * @param smsTime
	 */
	sendSmsTime : function(){
		if(CustWithdrawalClass.smsTime == 0){  
	   	 $('#send_sms_code_but').bind('click', CustWithdrawalClass.sendSmsCode);
	   	 $('#send_sms_code_but').html("发送验证码");  
	   	 $('#send_sms_code_but').removeClass("c798383"); 
	   	 $('#send_sms_code_but').addClass("c2980b9"); 
	   }else{
	   	  $('#send_sms_code_but').html(CustWithdrawalClass.smsTime--+" 秒后发送验证码");  
	   	  setTimeout("CustWithdrawalClass.sendSmsTime('"+CustWithdrawalClass.smsTime+"')",1000); 
	   }
	},
	/**
	 * 发送短信验证码
	 */
	sendSmsCode : function(){
		CustWithdrawalClass.smsTime = common.getParamValueByKey("SEND_SMS_TIME");
		var result = 2;// = CustWithdrawalClass.sendSmsCodePost();
		if(result == 1){
			$("#stepTwoMessage").html("该手机获取的验证码超过指定次数，请明日再试！");
			return;
		}else{
			$("#stepTwoMessage").html("");
			$("#send_sms_code_but").unbind("click");
			$('#send_sms_code_but').removeClass("c2980b9"); 
	    	$('#send_sms_code_but').addClass("c798383"); 
	    	CustWithdrawalClass.sendSmsCodePost();
	    	CustWithdrawalClass.sendSmsTime(CustWithdrawalClass.smsTime);
		}
	},
	/**
	 * 发送短信验证码
	 * @returns {String}
	 */
	sendSmsCodePost : function(){
		var money= $("#amount_id").val();
		var result = "";
		if(money == ""){
			CustWithdrawalClass.smsTime = 0;
			layer.msg('请您先填写金额!', {icon: 5});
			return;
		}
		$.ajax( {
			type : "POST",
			url : BASE_PATH+"userinfo/custRechargeWithdrawController/sendSmsCode.shtml?money="+money,
			async : false,
			dataType : "json",
			success : function(data) {
				if(data.message.status == 200 ){
					result = data.result;
				}else{
					CustWithdrawalClass.smsTime = 0;
					layer.msg('发送短信验证码失败啦!', {icon: 5});
				}
			}
		}); 
		return result;
	},
	/**
	 * 客户提现
	 */
	custWithdrawal : function(){
		$("#amount_alert").css('display','none');
		$("#amount_alert").css('display','none');
		$("#amount_alert").css('display','none');
		$("#trade_password_alert").css('display','none');
		$("#code_alert").css('display','none');
		$("#check_bank_alert").css('display','none');
		var bankId= $("#bank_id").val();
		//判断是否选中银行卡
		if(!bankId || bankId == ""){
			$("#check_bank_alert").css('display','block');
			return;
		}
		var params = jqueryUtil.serializeObject($("#pay_balance_withdrawal_form"),true);
		var url = BASE_PATH+"userinfo/custRechargeWithdrawController/custWithdrawal.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:params,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.message.message == 500){
		    			if(data.data==1){
		    				$("#trade_password_alert").css('display','block');
		    			}else if(data.data==2){
		    				$("#code_alert").css('display','block');
		    			}else if(data.data==3){
		    				layer.alert("提现金额不能为负数! 小逗比",{icon: 5});
		    			}else if(data.data==4){
		    				layer.alert("提现金额大于当前可提现总金额!",{icon: 5});
		    			}else if(data.data==5){
		    				layer.alert("逾期客户禁止提现!",{icon: 5});
		    			}
		    		}else{
		    			parent.location.replace( BASE_PATH+"/userinfo/custRechargeWithdrawController/toPayIndex.shtml?openStatus=2");
		    			//parent.location.reload();
		    		}
		    	}else{
		    		if(data && data.message && data.message.message=="tradePwdBank"){
		    			layer.alert("用户交易密码未设置, 请您设置交易密码之后在进行提现操作!<br/> " +
		    				"<a href='"+BASE_PATH+"securityCenter/bankController/personalData.shtml'>设置交易密码<a/>",{icon: 5});
		    		}else{
		    			layer.alert("数据加载失败,请联系管理员!",{icon: 5});
		    		}
		    	}
			}
		});
	},
	/**
	 * 打开银行卡管理页面
	 */
	openBankManager : function(){
		$("#withdraw_div").empty();
		//跳转到银行卡列表
		$("#withdraw_div").load( BASE_PATH+'userinfo/custRechargeWithdrawController/toBankList.shtml');
	},
	/**
	 * 加载银行卡数据
	 */
	loadData : function(){
		var url = BASE_PATH + "securityCenter/bankController/selectBankListByCusId.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		CustWithdrawalClass.htmlWithdrawaBankList(data.data);
		    	}else{
		    		layer.alert("数据加载失败,请联系管理员!",{icon: 5});
		    	}
			}
		});
	},
	/**
	 * 校验是否大于可提现金额
	 */
	checkWithdrawlAmount:function(){
		if("" == $("#amount_id").val()){
			$("#amount_id").focus();
			$("#amount_is_null").css('display','block');
		}else{
			$("#amount_is_null").css('display','none');
		}
		if(isNaN($("#amount_id").val())){
			$("#amount_id").focus();
			$("#amount_alert").css('display','block');
			return;
		}else{
			$("#amount_alert").css('display','none');
		}
		//最大可提现金额
		var cwa =  parseFloat($("#can_withdrawal_amount").val());
		//实际提现金额
		var ai = parseFloat($("#amount_id").val()).toFixed(2);
		$("#amount_id").val(ai);
		if(ai > cwa){
			$("#amount_id").focus();
			$("#if_amount_max").css('display','block');
			return;
		}else{
			$("#if_amount_max").css('display','none');
		} 
		if(ai < 0){
			$("#amount_id").focus();
			$("#if_amount_minus").css('display','block');
			return;
			//layer.alert("提现金额不能为负数!",{icon: 5});
		}else{
			$("#if_amount_minus").css('display','none');
		}
		
	}
	
}

$(function(){
	CustWithdrawalClass.loadData();
	 $('#send_sms_code_but').bind('click', CustWithdrawalClass.sendSmsCode);
	 $('#amount_id').bind('blur', CustWithdrawalClass.checkWithdrawlAmount);
});
