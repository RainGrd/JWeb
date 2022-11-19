
var TransferRevoke = {
		openWin:function(id,totalAmount,successAmount,returnDate,yearDate,netReceiptAmount){
			
			
			TransferRevoke.setValue(id,totalAmount,successAmount,returnDate,yearDate,netReceiptAmount);
			
			//撤销弹窗
			layer.open({
			    type: 1,
			    title: false,
			    shadeClose: true,
			    shade: 0.8,
			    offset: '150px',
			    area: ['710px','510px'],
			    content:$("#revoke")//iframe的url
			});
		},
		setValue:function(id,totalAmount,successAmount,returnDate,yearRate,netReceiptAmount){
			$("#tradeError").text("");
			$("#pwd").val("");
			$("#transferId").val(id);
			$("#totalAmount").text("￥"+totalAmount);
			$("#returnDate").text(returnDate+"天");
			$("#transferAmount").text("￥"+successAmount);
			$("#investAmount").text("￥"+netReceiptAmount);
			$("#yr").text(yearRate+"%");
			
		},
		// 撤销
		revoke:function(){
			if(TransferRevoke.validateData()){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"mybids/transferController/revokeTransfer.shtml",
			    	data:$("#revokeForm").serialize(),
					dataType: "json",
				    success: function(data){
				    	if(data.message.status == 200 ){
				    		layer.closeAll();
				    		layer.msg(CLAIM_TRANSFER.REVOKE_SUC,{icon:1});
				    		//进行刷新
				    		$("#transferingClaimPage").page('remote');
				    	}else{
				    		if(data.errorCode == 'tradePwdError'){
				    			// 交易密码错误
				    			$("#tradeError").text(USER_PWD_MSG.TRADE_ERROR_MSG);
				    		}else if(data.errorCode == 'tradePwdBank'){
				    			$("#tradeError").html(USER_PWD_MSG.TO_SET_TRADE_PWD);
				    		}else{
				    			// 撤销失败了
				    			layer.closeAll();
				    			//撤销弹窗
				    			layer.msg(CLAIM_TRANSFER.REVOKE_ERROR,{icon:5});
				    			//进行刷新
					    		$("#transferingClaimPage").page('remote');
				    		}
				    	}
					}
				}); 
			}
		},
		// 校验数据
		validateData:function(){
			$("#tradeError").text("");
			if($("#pwd").val() == null || $("#pwd").val().trim() == ''){
				$("#tradeError").text(USER_PWD_MSG.TRADE_BLANK_MSG);
				return false;
			}
			return true;
		}
}

/*清空错误提示*/
function clearErrorMsg(){
	$("#tradeError").text("");
}