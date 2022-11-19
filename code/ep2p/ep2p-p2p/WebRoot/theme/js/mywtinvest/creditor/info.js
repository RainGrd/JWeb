function agree(){
	return $('.gou_s').attr("val") == '1';
}
function buy() {
	if (validateData()) {
		
		$("#rsaPwd").val(rsa.encode64($("#pwd").val()));
		
		$.ajax({
			type : "POST",
			async : false,
			url : BASE_PATH + "/mybids/transferController/buyTransfer.shtml",
			data : $("#buyTransferForm").serializeArray(),
			dataType : "json",
			success : function(data) {
				if (data.message.status == 200) {
					layer.close(index);
	    			var i = layer.open({
	    				type : 1,
	    				title : false,
	    				shadeClose : false,
	    				shade : 0.5,
	    				offset : '150px',
	    				area : [ '700px', '420px' ],
	    				content : $("#buyClaimSuc"),
	    				cancel:function(index){
	    					window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
	    				}
	    			});
	    			
					CLAIM.countSuc(10);
				}else if(data.errorCode == 'tradePwdError'){
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
	    			layer.close(index);
	    			var i = layer.open({
	    				type : 1,
	    				title : false,
	    				shadeClose : false,
	    				shade : 0.5,
	    				offset : '150px',
	    				area : [ '700px', '420px' ],
	    				content : $("#buyClaimError"),
	    				cancel:function(index){
	    					window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
	    				}
	    			});
	    			
	    			CLAIM.countFail(10);
	    		}
			}
		});
	}
}
/* 交易表单数据 */
function validateData() {
	$("#tradeError").text("");
	$("#tradeError").hide();
	if ($("#pwd").val() == null || $("#pwd").val().trim() == '') {
		$("#tradeError").show();
		$("#tradeError").text(USER_PWD_MSG.TRADE_BLANK_MSG);
		return false;
	}
	return true;
}
function countDown(d) {
	if (d <= 0) {
		// 失效
		var btn = ' <a class="btn_gray dropJionBtn mt50" href="javascript:void(0)">立即购买</a>';
		$("#btn").html(btn);
		$("#countDown").text("00时00分00秒");
		// 并且关闭掉弹窗
		if (index > -1) {
			layer.close(index);
		}
		return false;
	}

//	var day = parseInt(d / 60 / 60 / 24);
	var h = parseInt(d / 60 / 60);
	var m = parseInt(d / 60 % 60);
	var s = parseInt(d % 60);

//	day = day > 9 ? day : "0" + day;
	h = h > 9 ? h : "0" + h;
	m = m > 9 ? m : "0" + m;
	s = s > 9 ? s : "0" + s;
	var time = h + "小时" + m + "分" + s + "秒";
	$("#countDown").text(time);
	d = d - 1;
	setTimeout("countDown(" + d + ")", 1000);
}

/* 购买确认 */
function okBuy() {
	if(!agree()){
		layer.msg(PROTOCOL_MSG.TRANSFER, {icon: 5});
		return;
	}
	// 清空表单
	$("#tradeError").text("");
	$("#tradeError").hide();
	$("#pwd").val("");

	// 撤销弹窗
	index = layer.open({
		type : 1,
		title : false,
		shadeClose : true,
		shade : 0.5,
		offset : '150px',
		area : [ '700px', '420px' ],
		content : $("#okBuy")
	});
	$("#pwd")[0].focus();
}
var CLAIM = {
		// 倒计时
		countSuc:function(time){
			$("#sc").text(time);
			time--;
			setTimeout("CLAIM.countSuc(\'"+time+"\')",1000);
			if(time < 1){
				window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
			}
		},
		countFail:function(time){
			$("#sc2").text(time);
			time--;
			setTimeout("CLAIM.countFail(\'"+time+"\')",1000);
			if(time < 1){
				window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
			}
		}
		
}