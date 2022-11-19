
/*键入转让价格时，改变对应的值*/
function inputTransferEvent(){
	var transferAmount = $("#transferAmount").val();
	$("#errorMsg").text("");
	$("#errorMsg").hide();
	if(isNaN(parseFloat(transferAmount))){
		$("#errorMsg").show();
		$("#errorMsg").text("请输入正确的转让价格");
		return false;
	}
	transferAmount = parseFloat(transferAmount);
	if(transferAmount < minValue || transferAmount > maxValue ){
		// 超出区间值
		$("#errorMsg").show();
		$("#errorMsg").text("超出允许的范围,请调整转让价格");
		return false ;
	}
	return true;
}
var n;
function next(){
	if(inputTransferEvent()){
		// 转让价格
		var transferAmount = $("#transferAmount").val();
		$("#transferMoney").text('￥'+accounting.formatNumber(transferAmount,2));
		$("#transferMoney2").text('￥'+accounting.formatNumber(transferAmount,2));
		$("#z_transferAmount").val(transferAmount);
		// 转让手续费
		var fee = parseFloat(bizTransferFee)*transferAmount;
		$("#transferFee").text('￥'+accounting.formatNumber(fee,2));
		$("#transferFee2").text('￥'+accounting.formatNumber(fee,2));
		// 清空交易密码
		$("#tradePassword").val('');
		$("#tradeError").hide();
		//转让成功弹窗
		n = layer.open({
		    type: 1,
		    title: false,
		    shadeClose: false,
		    shade: 0.5,
		    scrollbar:false,
		    offset:['15px'],
		    area:['700px','715px'],
		    content:$("#confirmWin"),
		    cancel:function(index){
		    	layer.close(n);
		    }
		});
	}
}

// 关闭转让确认弹窗
function closeConfirmWin(){
	layer.close(n);
}


/*提交*/
function transferSubmit(){
	if(!agree()){
		layer.msg(PROTOCOL_MSG.TRANSFER, {icon: 5});
		return;
	}
	if(inputTransferEvent()){
		// 校验交易密码
		if($("#tradePassword").val() == null || $("#tradePassword").val().trim() == ''){
			// 密码不能为空
			$("#tradePassword").val("");
			$("#tradeError").text(USER_PWD_MSG.TRADE_BLANK_MSG);
			$("#tradeError").show();
			return;
		}
		
		$("#pwd").val(rsa.encode64($("#tradePassword").val()));
		console.log(1);
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"usercenter/borrowDetailController/updateReceiptPlanById.shtml",
	    	data:$("#transferForm").serialize(),
			dataType: "json",
		    success: function(data){
		    	if(data.message.status == 200 ){
		    		$("#transferCode").text(data.transferCode);
		    		//转让成功弹窗
		    		$("#suc_msg").show();
		    		$("#transferPage").hide();
					layer.closeAll();
		    	}else{
		    		//转让失败
		    		if(data.errorCode == 'tradePwdError'){
						$("#tradeError").text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
						$("#tradeError").show();
					}else if(data.errorCode == 'tradePwdBank'){
						$("#tradeError").html(USER_PWD_MSG.TO_SET_TRADE_PWD);
						$("#tradeError").show();
					}else if(data.errorCode == 'tradeFreeze'){
						$("#tradeError").text("交易密码超过上限，为保护账户安全，系统禁止交易"+data.t+"分钟");
						$("#tradeError").show();
					}else{
			    		//转让失败弹窗
		    			layer.msg(CLAIM_TRANSFER.TRANSFER_ERROR, {icon: 5});
		    		}
		    	}
			}
		}); 
	}
}

/*清空错误提示信息*/
function clearErrorMsg(){
	$("#tradeError").text("");
	$("#tradeError").hide();
}

function agree(){
	return $('.gou_s').attr("val") == '1';
}