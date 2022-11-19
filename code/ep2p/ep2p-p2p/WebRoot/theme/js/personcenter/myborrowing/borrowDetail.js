/**
 * 个人中心_我的借款JS类
 */
var borrowDetail = {
	initData:function(){
		// 还款方式默认选中
		$(".radio_s[val=1]").addClass("c2980b9").html("<img src='/ep2p/theme/default/images/radio_x.png' class='block'>");
		
		// 确认还款事件绑定
		$(".loanInfoBtn").click(function(){
			var val = $(".c2980b9").attr("val");
			if(val == 1){
				// 立即还款
				$("#payloanInfoPopUp").show();
			}else if (val == 2){
				// 提前还款
				$(".loanInfoPopUp").show();
			}
			
		});
		
		// 提前还款确认时间绑定
		$(".loanPopupSuessBtn").click(function(){
			var val = $(".c2980b9").attr("val");
			if(val == 1){
				borrowDetail.payrepayment();
			}else if (val == 2){
				// 提前还款
				borrowDetail.prerepayment();
			}
		});
		
		// 
		$(".loanPopClose").click(function(){
			$(".loanInfoPopUp").hide();
			//$(".dropPopupBox2").fadeIn();
		});
		
		// 还款方式选择事件绑定
		radios_(".loanInfoForm",".radio_xiu1");
	},
	// 提前还款确认
	prerepayment:function(){
		var promptErrorId = "#promptError";
		// 执行提前还款
		$.ajax({
			type: "POST",
	    	url : BASE_PATH + "business/myLoanController/prerepayment.shtml?borrowId="+$("#borrowId").val()+"&tradePassword="+$("#tradePassword_2").val(),
			dataType: "json",
		    success: function(data){
		    	if(data.message.status == 200 ){
		    		$(".dropPopupBox2").show();
					$(".vip_g_d").hide();
		    	}else{
		    		// 兑换失败,交易密码错误
		    		if(data.errorCode == 'tradePwdError'){
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
		    		}else if(data.errorCode == 'tradePwdBank'){
		    			// 您还没有设置交易密码，去设置。
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).html(USER_PWD_MSG.TO_SET_TRADE_PWD);
		    		}else if(data.errorCode == "availableAmountError"){
		    			// 可用余额不够
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).html(PREREPAYMENT.AVAILABLE_AMOUNT_NO);
		    		}else if(data.errorCode == "tradeFreeze"){
		    			// 交易密码被锁
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).html(POINT_EXCHANGE.EXCHANGE_PASSWROD_FREEZE+data.t+"分钟!");
		    		}else{
			    		// 提前还款失败弹窗
		    			layer.msg(PREREPAYMENT.PREREPAYMENT_ERROR, {icon: 5});
		    		}
		    	}
			}
		});
	},
	payrepayment:function(){
		var payrepayErrorId = "#payrepayError";
		var repayAmount = $("#repayAmount").html();
		var availableAmount = $("#availableAmount").html();
		if (parseInt(availableAmount)<parseInt(repayAmount)) {
			$(payrepayErrorId).removeClass("none_");
			$(payrepayErrorId).show();
			$(payrepayErrorId).html(PREREPAYMENT.AVAILABLE_AMOUNT_NO);
			return;
		}
		// 执行立即还款
		$.ajax({
			type: "POST",
	    	url : BASE_PATH + "business/myLoanController/payrepayment.shtml?borrowId="+$("#borrowId").val()+"&tradePassword="+$("#tradePassword").val(),
			dataType: "json",
		    success: function(data){
		    	if(data.message.status == 200 ){
		    		$("#payRepayResult").show();
					$(".vip_g_d").hide();
		    	}else{
		    		// 兑换失败,交易密码错误
		    		if(data.errorCode == 'tradePwdError'){
		    			// 去掉隐藏提示框的样式
		    			$(payrepayErrorId).removeClass("none_");
		    			$(payrepayErrorId).show();
		    			$(payrepayErrorId).text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
		    		}else if(data.errorCode == 'tradePwdBank'){
		    			// 您还没有设置交易密码，去设置。
		    			// 去掉隐藏提示框的样式
		    			$(payrepayErrorId).removeClass("none_");
		    			$(payrepayErrorId).show();
		    			$(payrepayErrorId).html(USER_PWD_MSG.TO_SET_TRADE_PWD);
		    		}else if(data.errorCode == "availableAmountError"){
		    			// 可用余额不够
		    			// 去掉隐藏提示框的样式
		    			$(payrepayErrorId).removeClass("none_");
		    			$(payrepayErrorId).show();
		    			$(payrepayErrorId).html(PREREPAYMENT.AVAILABLE_AMOUNT_NO);
		    		}else if(data.errorCode == "tradeFreeze"){
		    			// 交易密码被锁
		    			// 去掉隐藏提示框的样式
		    			$(payrepayErrorId).removeClass("none_");
		    			$(payrepayErrorId).show();
		    			$(payrepayErrorId).html(POINT_EXCHANGE.EXCHANGE_PASSWROD_FREEZE+data.t+"分钟!");
		    		}else{
			    		// 提前还款失败弹窗
		    			layer.msg(PREREPAYMENT.PREREPAYMENT_ERROR, {icon: 5});
		    		}
		    	}
			}
		});
	}
	
}

function radios_(a,b){
   $(a +" .radio_s").click(function(){
		$(a +" "+b).removeClass("c2980b9");
		$(a +" "+b).eq($(this).index(a +" .radio_s")).addClass("c2980b9");
		$(a +" .radio_s").html("");
		$(this).html('<img src="/ep2p/theme/default/images/radio_x.png" class="block" />');
	})
}

$(document).ready(function(){
	borrowDetail.initData();
});