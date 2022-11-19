var index;
var newStandard = {
		// 新手标投资输入
		investEvent:function(){
			var r = true;
			var amount = $("#amount").val();
			if(amount == null || amount == ''){
				$("#amount")[0].focus();
				return;
			}
			var invest = newStandard.countInvest(amount);
			$("#invest").text("￥"+invest);
			// 设置积分
			$("#pointValue").text((parseInt(pointValue*amount))+"分");
			$("#pointValue2").text((parseInt(pointValue*amount))+"分");
			// 设置金额
			$("#am").val(amount);
			// 设置预计收益
			$("#ci").text("￥"+invest);
			
			$("#s_amount").text("￥"+amount);
			$("#s_invest").text("￥"+invest);
			
			$("#s_amount2").text("￥"+amount);
			$("#s_invest2").text("￥"+invest);
			
			r = newStandard.validateAmount(amount);
			
			return r;
		},
		// 新手标投资输入
		investEvent2:function(){
			var r = true;
			var amount = $("#am").val();
			if(amount == null || amount == ''){
				$("#amount")[0].focus();
				return;
			}
			var invest = newStandard.countInvest(amount);
			$("#invest").text("￥"+invest);
			// 设置积分
			$("#pointValue").text((parseInt(pointValue*amount))+"分");
			$("#pointValue2").text((parseInt(pointValue*amount))+"分");
			// 设置金额
			$("#amount").val(amount);
			// 设置预计收益
			$("#ci").text("￥"+invest);
			
			$("#s_amount").text("￥"+amount);
			$("#s_invest").text("￥"+invest);
			
			$("#s_amount2").text("￥"+amount);
			$("#s_invest2").text("￥"+invest);
			
			r = newStandard.validateAmount(amount);
			
			return r;
		},
		// 预计收益  加入金额 * （年化率 / 360） * 借款期限
		countInvest:function(amount){
			var invest = (amount * borDeadline * borrowRate / 360).toFixed(2);
			return invest;
		},
		// 投资确认
		confirmOk:function(){
			if(!newStandard.investEvent()){
				return;
			}
			var gou = $("#gou").attr("val");
			if(gou != '1'){
				layer.alert(NEW_STANDARD.PROTOCOL_MSG, {icon: 5,offset : '300px'});
				return;
			}
			$("#pwd").val("");
			
			index = layer.open({
				type : 1,
				title : false,
				shadeClose : true,
				shade : 0.5,
				offset : '150px',
				area : [ '700px', '520px' ],
				content : $("#investOK")
				
			});
			$("#pwd")[0].focus();
		},
		// 校验投资金额是否合法
		validateAmount:function(amount){
			amount = parseFloat(amount);
			var r = true;
			var msg = "";
			$("#imsg").text(msg);
			$("#imsg2").text(msg);
			newStandard.showOrHide(false);
			// 可用余额
			var avamount = $("#avamount").val();
		
			if(amount<=0){
				//判断投资金额是否小于等于0
				msg = NEW_STANDARD.INPUT_AMOUNT;
				r = false;
				$("#imsg2").text(msg);
				$("#imsg2").show();
			}else if(amount > parseFloat(sMoney)){
				//判断投资金额是否小于剩余金额
				msg = "投资金额需小于￥"+accounting.formatNumber(sMoney,2);
				r = false;
				$("#imsg2").text(msg);
				$("#imsg2").show();
			}else if(amount > avamount){
				//判断投资金额是否小于可用余额
				newStandard.showOrHide(true);
				r = false;
			}else if(amount < startMoney){
				//判断投资金额是否在投资范围
				msg = "投资需大于￥"+accounting.formatNumber(startMoney,2);
				r = false;
				$("#imsg2").text(msg);
				$("#imsg2").show();
			}else{
				// 如果是倍数投资，判断投资金额是否是该数的倍数
				if(endMoney > 0){
					if(amount > endMoney){
						msg = "投资范围应该在￥"+accounting.formatNumber(startMoney,2)+"~￥"+accounting.formatNumber(endMoney,2);
						r = false;
						$("#imsg2").text(msg);
						$("#imsg2").show();
					}
				}
				if(isTimesInvest == '1'){
					if(amount % parseInt(startMoney) != 0){
						// 不是倍数投资
						msg = "投资金额必须是"+parseInt(startMoney)+"的倍数";
						r = false;
					}
				}
				$("#imsg2").text(msg);
				$("#imsg2").show();
			}
			$("#imsg").text(msg);
			if(!r){
				$("#imsg").show();
				$("#imsg2").show();
			}else{
				$("#imsg").hide();
				$("#imsg2").hide();
			}
			return r;
		},
		// 控制充值显示隐藏
		showOrHide:function(type){
			if(type){
				var rechargeUrl = NEW_STANDARD.TO_RECHARGE;
				$("#recharge").html(rechargeUrl);
				$("#recharge2").html(rechargeUrl);
			}else{
				$("#recharge").html("");
				$("#recharge2").html("");
			}
		},
		closePop:function(){
			layer.close(index);
		},
		openSuccess:function(){
			
			index = layer.open({
				type : 1,
				title : false,
				shadeClose : false,
				shade : 0.5,
				offset : '150px',
				area : [ '700px', '471px' ],
				content : $("#suc"),
				cancel:function(index){
					window.location.reload();
				}
				
			});
			newStandard.countSuc(10);
			
		},
		openFailure:function(msg){
			index = layer.open({
				type : 1,
				title : false,
				shadeClose : false,
				shade : 0.5,
				offset : '150px',
				area : [ '700px', '471px' ],
				content : $("#fail"),
				cancel:function(index){
					window.location.reload();
				},
				 success: function(layero, index){
			        if(msg != null  && msg !=""){
			        	$("#e_msg").text(msg);
			        }
			    }
				
			});
			newStandard.countFail(10);
		},
		// submit
		submit:function(){
			if(newStandard.investEvent()){
				$("#pwdError").text("");
				$("#pwdError").hide();
				if($("#pwd").val() == null || $("#pwd").val().trim() == ''){
					$("#pwdError").text(USER_PWD_MSG.TRADE_BLANK_MSG);
					$("#pwdError").show();
					return;
				}
				var i = layer.load(0,{time: 10*1000}); //并且设定最长等待10秒 
				
				$("#rsaPwd").val(rsa.encode64($("#pwd").val()));
				
				$.ajax({
					type : "POST",
					async : false,
					url : BASE_PATH + "/mybids/borrowNewStandardController/investStandard.shtml",
					data : $("#investForm").serialize(),
					dataType : "json",
					success : function(data) {
						if (data.message.status == 200) {
							layer.close(i);
							// 关闭投资确认
							newStandard.closePop();
							// 打开投资成功页面
							newStandard.openSuccess();
						} else {
							if(data.errorCode == 'tradePwdError'){
								layer.close(i);
								$("#pwdError").text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
								$("#pwdError").show();
							}else if(data.errorCode == 'tradePwdBank'){
								layer.close(i);
								$("#pwdError").html(USER_PWD_MSG.TO_SET_TRADE_PWD);
								$("#pwdError").show();
							}else if(data.errorCode == 'tradeFreeze'){
								layer.close(i);
								$("#pwdError").text("交易密码超过上限，为保护账户安全，系统禁止交易"+data.t+"分钟");
								$("#pwdError").show();
							}else{
								layer.close(i);
								// 关闭投资确认
								
								newStandard.closePop();
								
								var msg = '';
								if(data.errorMsg!=null && ''!=data.errorMsg){
									msg = "投标失败了，"+data.errorMsg;
								}
								newStandard.openFailure(msg);
								
								
							}
						}
					}
				});
			}
		},
		// 
		clearPwd:function(){
			$("#pwdError").text("");
			$("#pwdError").hide();
		},
		// 倒计时
		countSuc:function(time){
			$("#sc").text(time);
			time--;
			setTimeout("newStandard.countSuc(\'"+time+"\')",1000);
			if(time < 1){
				window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
			}
		},
		countFail:function(time){
			$("#sc2").text(time);
			time--;
			setTimeout("newStandard.countFail(\'"+time+"\')",1000);
			if(time < 1){
				window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
			}
		},
		detail:function(){
			var url = BASE_PATH+"/mybids/borrowNewStandardController/index/investStandardRecord.shtml?pid="+borrowId;
			/*分页*/
			$("#page").page({
				pageSize : 10,    // 测试设置为1
				showInfo : false,
				showJump : false,
				showPageSizes : false,
				loadFirstPage : true,
				remote : {
					url : url,
					success : function(result, pageIndex) {
						setData(result.list);
					},
					pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
					pageSizeName : 'pageSize'//请求参数，每页数量
				}
			});

			
		}
}


function countDown(d) {
	if (d <= 0) {
		// 失效
		if(borStatus == '1'){
			$("#btn").hide();
			$("#btn2").show();
			borStatus = '2';
			countDown(t);
		}else{
			var btn = '<a class="btn_gray dropJionBtn" href="javascript:void(0)">立即加入</a>';
			$("#btn").html(btn);
			$("#btn2").html(btn);
			$("#countDown").text("00天00时00分00秒");
		}
		// 并且关闭掉弹窗
		if (index > -1) {
			layer.close(index);
		}
		return false;
	}

	var day = parseInt(d / 60 / 60 / 24);
	var h = parseInt(d / 60 / 60 % 24);
	var m = parseInt(d / 60 % 60);
	var s = parseInt(d % 60);

	day = day > 9 ? day : "0" + day;
	h = h > 9 ? h : "0" + h;
	m = m > 9 ? m : "0" + m;
	s = s > 9 ? s : "0" + s;
	var time = day + "天" + h + "小时" + m + "分" + s + "秒";
	$("#countDown").text(time);
	d = d - 1;
	setTimeout("countDown(" + d + ")", 1000);
}

/**投资记录*/
function setData(list){
	var html  = '';
	for(var i=0;i<list.length;i++){
		var customerName = list[i].privateName;
		var type = list[i].investmentType == '1' ? '手动':'自动';
		var amount = "￥"+ accounting.formatNumber(list[i].investmentAmount,2);
		html += '<tr>'+
                 '<td>'+customerName+
                 '<td>'+type+'</td> '+
                 '<td>'+amount+'</td> '+
                 '<td>'+list[i].investmentTime+'</td> '+
                 '<td>成功</td> '+
                '</tr> ';
	}
	$("#detaiList").html(html);
}