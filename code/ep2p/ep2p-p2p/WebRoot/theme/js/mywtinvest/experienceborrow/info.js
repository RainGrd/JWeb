var index;
var experienceBorrow = {
		toExperienceBorrow:function(pid){
			window.location.href=BASE_PATH+"/mybids/experienceBorrowController/index/toBorrowStandardInfo.shtml?pid="+pid;
		},
		// 体验标投资输入
		investEvent:function(){
			var r = true;
			var amount = $("#amount").val();
			if(amount == null || amount == ''){
				$("#amount")[0].focus();
				return;
			}
			var invest = experienceBorrow.countInvest(amount);
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
			
			r = experienceBorrow.validateAmount(amount);
			
			return r;
		},
		// 体验标投资输入
		investEvent2:function(){
			var r = true;
			var amount = $("#am").val();
			if(amount == null || amount == ''){
				$("#amount")[0].focus();
				return;
			}
			var invest = experienceBorrow.countInvest(amount);
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
			
			r = experienceBorrow.validateAmount(amount);
			
			return r;
		},
		// 预计收益  加入金额 * （年化率 / 360） * 借款期限
		countInvest:function(amount){
			var invest = (amount * borDeadline * borrowRate / 36000).toFixed(2);
			return invest;
		},
		// 投资确认
		confirmOk:function(){
//			if(!experienceBorrow.investEvent()){
//				return;
//			}
			var gou = $("#gou").attr("val");
			if(gou != '1'){
				layer.alert('尊敬的用户，您还未同意《e生财富借款协议》', {icon: 5,offset : '300px'});
				return;
			}
			$("#pwd").val("");
			
			index = layer.open({
				type : 1,
				title : false,
				shadeClose : true,
				shade : 0.5,
				offset : '150px',
				area : '700px;',
				content : $("#investOK")
				
			});
			$(".drop_list").attr("eq","0");
			$(".drop_list").click(function(){
				var eq = $(this).index(".drop_list");
				if($(this).attr("eq")==0){
					 $(this).removeClass("drop_list_bg_b").addClass("drop_list_bg_l");
					 $(this).attr("eq","1");
					 $(this).find("input").eq(0).removeAttr("disabled");
				}else{
					 $(this).removeClass("drop_list_bg_l").addClass("drop_list_bg_b");
					 $(this).attr("eq","0");
					 $(this).find("input").eq(0).attr("disabled","disabled");
				}
				estimateInvest();
			});

		},
		// 校验投资金额是否合法
		validateAmount:function(amount){
			amount = parseFloat(amount);
			var r = true;
			var msg = "";
			$("#imsg").text(msg);
			$("#imsg2").text(msg);
			experienceBorrow.showOrHide(false);
			// 可用余额
			var avamount = $("#avamount").val();
		
			if(amount<=0){
				//判断投资金额是否小于等于0
				msg = "输入投资金额";
				r = false;
				$("#imsg2").text(msg);
			}else if(amount > parseFloat(sMoney)){
				//判断投资金额是否小于剩余金额
				msg = "超出可投金额";
				r = false;
				$("#imsg2").text(msg);
			}else if(amount > avamount){
				//判断投资金额是否小于可用余额
				console.log("xxx:"+avamount);
				console.log("amount:"+amount);
				experienceBorrow.showOrHide(true);
				r = false;
			}else{
				// 如果是倍数投资，判断投资金额是否是该数的倍数
				if(isTimesInvest == '1'){
					if(amount % parseInt(startMoney) != 0){
						// 不是倍数投资
						msg = "投资金额必须是"+parseInt(startMoney)+"的倍数";
						r = false;
					}
				}
				$("#imsg2").text(msg);
			}
			$("#imsg").text(msg);
			
			return r;
		},
		// 控制充值显示隐藏
		showOrHide:function(type){
			if(type){
				var rechargeUrl = '<a href="">余额不足，去充值</a>';
				$("#recharge").html(rechargeUrl);
				$("#imsg2").text("余额不足");
				
			}else{
				$("#recharge").html("");
				$("imsg2").text("");
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
			experienceBorrow.countSuc(10);
			
		},
		openFailure:function(){
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
				}
				
			});
			experienceBorrow.countFail(10);
		},
		// submit
		submit:function(){
//			if(experienceBorrow.investEvent()){
//				$("#pwdError").text("");
				var epcesNull = true;
				$(".drop_list").each(function(index,item){
					if($(this).attr("eq")==1){
						epcesNull = false;
					}
				});
				if(epcesNull){
					layer.alert('请选择体验金卷！', {icon: 5,offset : '300px'});
					return;
				}
				if($("#pwd").val() == null || $("#pwd").val().trim() == ''){
					$("#pwdError").text(USER_PWD_MSG.TRADE_BLANK_MSG);
					$("#pwdError").show();
					return;
				}
				$("#rsaPwd").val(rsa.encode64($("#pwd").val()));//交易密码传输加密
				var i = layer.load(0,{time: 10*1000}); //又换了种风格，并且设定最长等待10秒 
				$.ajax({
					type : "POST",
					async : false,
					url : BASE_PATH + "/mybids/experienceBorrowController/investBorrow.shtml",
					data : $("#investForm").serialize(),
					dataType : "json",
					success : function(data) {
						if (data.message.status == 200) {
							layer.close(i);
							var amount = $('#am').val();
							var invest = $('#ci').html();
							// 关闭投资确认
							experienceBorrow.closePop();
							// 打开投资成功页面
							experienceBorrow.openSuccess();
							$('#s_amount').html(amount);
							$('#s_invest').html(invest);
							$('#s_amount2').html(amount);
							$('#s_invest2').html(invest);
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
							}else if(data.errorCode == 'borrowFull'){
								layer.close(i);
								layer.alert('该体验标已经满标了！', {icon: 5,offset : '300px'});
							}else if(data.errorCode == 'epcesIsNull'){
								layer.close(i);
								layer.alert('请选择体验金卷！', {icon: 5,offset : '300px'});
							}else if(data.errorCode == 'tradePwdIsNull'){
								layer.close(i);
								layer.alert('请输入交易密码！', {icon: 5,offset : '300px'});
							}else{
								layer.close(i);
								// 关闭投资确认
								experienceBorrow.closePop();
								experienceBorrow.openFailure();
							}
						}
					}
				});
			//}
		},
		// 
		clearPwd:function(){
			$("#pwdError").text("");
		},
		// 倒计时
		countSuc:function(time){
			$("#sc").text(time);
			time--;
			setTimeout("experienceBorrow.countSuc(\'"+time+"\')",1000);
			if(time < 1){
				window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
			}
		},
		countFail:function(time){
			$("#sc2").text(time);
			time--;
			setTimeout("experienceBorrow.countFail(\'"+time+"\')",1000);
			if(time < 1){
				window.location.href=BASE_PATH+"/index/homepController/toIndex.shtml";
			}
		},
		detail:function(){
			var url = BASE_PATH+"/mybids/experienceBorrowController/index/investBorrowRecord.shtml?pid="+borrowId;
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
		var btn = '<a class="btn dropJionBtn" style="background-color: gray">立即加入</a>';
		$("#btn").html(btn);
		$("#countDown").text("00天00时00分00秒");
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
		var sname = list[i].sname == null ? list[i].phoneNo : list[i].sname;
		var type = list[i].investmentType == '1' ? '手动':'手机';
		var amount = "￥"+ accounting.formatNumber(list[i].investmentAmount,2);
		html += '<tr>'+
                 '<td>'+sname+
                 '<td>'+type+'</td> '+
                 '<td>'+amount+'</td> '+
                 '<td>'+list[i].createTime+'</td> '+
                 '<td>成功</td> '+
                '</tr> ';
	}
	$("#detaiList").html(html);
}

function estimateInvest(){
	var money = parseInt(0);
	$(".drop_list").each(function(index,item){
		if($(this).attr("eq")==1){
			money+=parseInt($(this).find('i').eq(0).find('span').eq(0).html());
		}
		var invest= experienceBorrow.countInvest(money);
		$('#am').val(money.toFixed(2));
		$('#ci').html(invest);
		$('#tb').html(money.toFixed(2));
	});
}

(function(){	
	$(".email_key_js").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip_d").removeClass("none");
		$(".top1500").css("top","0");
		$(".vip_g_d").css("top",($(window).height()-575)/2);
		$(".vip_g_d").css("left",($(window).width()-700)/2);
	});
$(".drop_list").toggle(
		  function () {
			  $(this).removeClass("drop_list_bg_b").addClass("drop_list_bg_l");
			  $(this).attr("eq","1")
		  },
		  function () {
			  $(this).removeClass("drop_list_bg_l").addClass("drop_list_bg_b");
			  $(this).attr("eq","0")
		  }
	);	 
 var oThis=0;
 $(".pageIndex").html(1);
var l=$(".scrollBox ul").length-1;
$(".scrollBox ul").click(function(){
	oThis=$(this).index();
	//alert(oThis);
});
$(".scrollWrap .pagePre").click(function(){
	if(oThis>0){
		oThis--;
		$(".scrollBox").animate({"left":-oThis*570+"px"},500)
		$(".pageIndex").html(oThis+1);
	}
	else{oThis=0}
});
$(".scrollWrap .pageNext").click(function(){
	if(oThis<l){
		oThis++;
		$(".scrollBox").animate({"left":-oThis*570+"px"},500);
		$(".pageIndex").html(oThis+1);
		
	}
	else{oThis=l}
});
$(".pageLenght").html(l+1);
})();
	