$(function(){ 
 		selectAllPageByTypeId();  
	})
	/*
	查询 用户拥有的加息券
	*/
	function selectAllPageByTypeId(){
		var customerId = $("#customerId").val();
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/business/financialProductsManageController/index/selectAllPageByTypeId.shtml",
			data : {customerId:customerId},
			dataType : "json",
			success : function(data) {
				var html =" <span class='plr10 vip_radioa inline_block' >"+
								"<i class='radio_s radio_xiu1 m5' onclick='selectInvestAward(-1)' id='-1' val='0'><img src='"+ basePath + "theme/default/images/radio_x.png' class='block' /></i>无"+
							"</span>";
				if(data.actdetailList == null ){
					return;
				} 
				for(var i = 0 ; i < data.actdetailList.length ;i ++){
					html +='<span class="plr10 vip_radioa inline_block">'+
			                    '<i class="radio_s radio_xiu1 m5" onclick="selectInvestAward('+i+')" id="'+i+'" val="'+data.actdetailList[i].pid+'" cardQuota="'+data.actdetailList[i].cardQuota+'"></i>+'+escfutil.formatCurrency(data.actdetailList[i].cardQuota * 100)+
			                '%</span>'; 
				}
				  $("#jiaxi").append(html);
			},
			error : function() {
				layer.alert("查询预期收益接口异常!"); 
			}
		});
	}
	//立即投资
	function invest(){
		var accrualType = $("#accrualType").val();//计息类型  来确定计息时间 
		var startMoney = formatNull($("#startMoney").val());//起投金额  
		var endMoney = formatNull($("#endMoney").val());//投资上限
		var investNumber = $("#investmentAmount").val();//投资金额
		var availableAmount = formatNull($("#availableAmount").val());//账户可用余额
		var isTimesInvest = $("#isTimesInvest").val();//是否倍数投资
		var pwd = $("#pwd").val();
		//投资金额不能小于起投金额
		if(investNumber-startMoney< 0){
			$("#imsg2").text("起投金额不能小于："+startMoney+"元");
			$("#imsg2").show();
			return;
		} 
		//是否倍数投资
		if(isTimesInvest == 1){
			if(Number(investNumber)%Number(startMoney) !=0 ){
				$("#imsg2").text("投资金额必须是：“"+startMoney+"”的倍数");
				$("#imsg2").show();
				return;
			}  
		} 
		if(endMoney != 0 && endMoney != undefined && endMoney != '' ){
			investUp(investNumber,endMoney);
		} 
		//投资金额不能大于投资上限 
		if(availableAmount-investNumber<0){ 
				$("#imsg2").text("可用余额不足!");
				$("#imsg2").show();
				return 
		}
		$("#imsg2").hide();
		var borrowPassword = $("#borrowPassword").val(); 
		if($("#borrowPasswordDiv").is(":visible") && borrowPassword == "" ){
			$("#pwdError").text("请输入招标密码！");
			$("#pwdError").show();
			return;
		}
		if(pwd == "" ){
			$("#pwdError").text("请输入交易密码！");
			$("#pwdError").show();
			return;
		} 
		$.ajax({
			type: 'POST',
			url : BASE_PATH+"business/financialProductsManageController/save.shtml?pwd="+pwd+"&borrowPassword="+borrowPassword,
	    	data:$("#conditionForm").serializeArray(), 
			dataType: 'json',
			async:false,
		    success: function(data){
		    	//投资失败
		    	if(!data.retCode){
		    		if(data.errorCode == "tradePwdError"){
		    			$("#pwdError").text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
						$("#pwdError").show();
		    		}else if(data.errorCode == "tradePwdBank"){
		    			$("#pwdError").html(USER_PWD_MSG.TO_SET_TRADE_PWD); 
						$("#pwdError").show();
		    		}else if(data.errorCode == 'tradeFreeze'){
		    			$("#pwdError").text("交易密码超过上限，为保护账户安全，系统禁止交易"+data.t+"分钟");
						$("#pwdError").show();
		    		}else{
		    			$("#pwdError").text(data.retMessage);
						$("#pwdError").show();
		    		} 
		    	} 
		    	//新增成功
		    	if(data.retCode){
		    		//转让成功弹窗
					layer.open({
					    type: 1, 
					    closeBtn: 0,
					    title: '',
					    shadeClose: true,
					    shade: 0.8,
					    scrollbar:false,
					    area: ['700px', '523px'], 
					    offset: '0px',
					    content:$("#suc_msg") 
					}); 
					//十秒后跳转到  主页 
					timer(10);
					$("#m_pi").text("￥"+data.detail.investmentAmount);//投资金额 
		    	} 
		    },
		    error:function(data){
		    	window.parent.parent.location.href = BASE_PATH+'index/homepController/toIndex.shtml';
		    }
		});
	} 
	/**
	 * 本次投资金额或者历史投资金额 不能超过投资上限
	 * @param investNumber 本次投资金额
	 * @param endMoney 设定投资上限
	 */
	function investUp(investNumber,endMoney){
		var borrowId = $("#borrowId").val();
		if(endMoney!=0 && endMoney!=undefined){
			if(endMoney-investNumber<0){ 
				$("#pwdError").text("金额上限不能大于："+endMoney+"元");
				$("#pwdError").show();
				return;
			}
		}
		$.ajax({
			type: 'POST',
			url : BASE_PATH+"business/financialProductsManageController/index/getInvestTotalByBorrowId.shtml",
	    	data:{borrowId:borrowId}, 
			dataType: 'json',
			async:false,
		    success: function(data){
		    	if(data.investTotal+Number(investNumber)>Number(endMoney)){
		    		$("#pwdError").text("累计投资不能超过设置的投资上限，请选择其他标的！");
					$("#pwdError").show(); 
					return;
		    	};
		    }
		    });
		
	}
	function closeWindow(obj){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index); 
	}
	/**
	选择加息券
	*/
	function selectInvestAward(obj){ 
		$("#"+obj).parent().siblings().find("img").remove();
		$("#"+obj).html('<img src="'+ basePath + 'theme/default/images/radio_x.png" class="block" />');
		if(obj == "-1"){
			$("#investAwardId").val("");
		}else{
			$("#investAwardId").val($("#"+obj).attr("val")); 
		}
		//
		var a = $("#investmentAmount").val();
		$("#investAward").text("+"+escfutil.formatCurrency($("#"+obj).attr("cardQuota") * a)+"（加息收益）"); 
		$("#m_investAward").text("+"+escfutil.formatCurrency($("#"+obj).attr("cardQuota") * a)+"（加息收益）");  
	}
	/**
 	 * 投资金额输入框 失去光标事件
 	 * @param obj
 	 */
 	function count(obj){
		getEplanIntegral(obj);//获取积分
		$('#repaymentAmt').val(obj);
		$('#m_pi').text("￥"+escfutil.formatCurrency(obj));
		getInvestReward();//获取投资奖励
		
	}
 	
 	
 	/**
 	 * 得到奖励积分
 	 * @param obj 
 	 */
 	function getEplanIntegral(obj){
		var investNumber = obj;//输入的投资金额
		var customerId = $("#customerId").val();
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/business/financialProductsManageController/index/getEplanIntegral.shtml",
			data : {pointGetType:"eplan_integral",investmentMoney:investNumber,customerId:customerId},
			dataType : "json",
			success : function(data) {
				 $("#eplanIntegral").text(data.eplanIntegral);
				 $("#m_yearRate").text(data.eplanIntegral);
				 $("#eplanIntegralVal").val(data.eplanIntegral);
			},
			error : function() {
				layer.alert("查询积分接口异常!"); 
			}
		});
	}
 	
 	/**
	 * 得到预期收益 和  投资奖励
	 * @param obj 
	 */
	function getInvestReward(){
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/business/financialProductsManageController/index/getInvestReward.shtml",
			data : $("#investRewardForm").serializeArray(),
			dataType : "json",
			success : function(data) {  
				 $("#expectAmount").text("￥"+escfutil.formatCurrency(data.eplanIntegral));//预期收益
				 $("#m_transferAmount").text("￥"+escfutil.formatCurrency(data.eplanIntegral));//预期收益
				 $("#investReward").text(escfutil.formatCurrency(data.investmentReward));//投资奖励
				 $("#m_money").text("￥"+escfutil.formatCurrency(data.investmentReward));//投资奖励
				 $("#expectAmountVal").val(data.eplanIntegral);//预期收益
				 $("#investRewardVal").val(data.investmentReward);//投资奖励
				},
			error : function() {
				layer.alert("查询预期收益接口异常!"); 
			}
		});
	}
	/**
	 * 十秒后跳转到主页
	 */
	function timer(ss){  
		//投资成功页面是否显示
		if($("#suc_msg").is(":visible")){ 
			if (ss > 0) { 
				setTimeout("timer('"+(ss-1)+"')", 1000);
				$("#temp").text(ss); 
				return
			}   
			window.open(BASE_PATH +"/index/homepController/toIndex.shtml" , "_parent" ) ;
			//关闭倒计时
			clearTimeout(timer);  
		}
	} 
	/**
	 * 格式化 null Undefined 
	 * @param value 需要格式化的值
	 */
	function formatNull(value){
		//如果传入值为数字  为空返回0
		 if(typeof(value)=="number"){
			 if(value == undefined || value == null){
				 return 0;
			 }else{
				 return value;
			 }
		 }else{
			 //只要不是数字  为空一律返回""
			 if(value == undefined || value == null){
				 return "";
			 }else{
				 return value;
			 }
		 }
	} 