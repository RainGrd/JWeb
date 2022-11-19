var yin =["工商银行","农业银行","建设银行","招商银行","民生银行","交通银行","中国银行","中信银行","广发银行","兴业银行","华夏银行","上海银行","北京银行","光大银行","平安银行","中国信合","广州银行","南京银行","深圳发展银行","邮政银行","浦发银行","江苏银行","成都银行","成都农商银行","南充市商业银行","渤海银行","郑州银行","杭州银行","宁波银行","顺德农商银行","华融湘江银行","湖北银行","晋商银行","广州农商银行","常熟农商银行","东莞银行"];
function f_(y_){
	for(var i=0;i<36;i++){
		if (y_==yin[i]) {
			return i+1;
		}
	}
}
/*切换效果*/
function qie(a,b,c){
	
	$(a).click(function(){
		var eq = $(this).index(a);
		$(a).removeClass(c).eq(eq).addClass(c);
		$(b).addClass("none").eq(eq).removeClass("none");
		$(".leiji_js").addClass("none").eq(eq).removeClass("none");
	});
}

/*加载数据*/
function loadData(flag,elId){
	var url = "";
	var pageElId = "";
	if(flag == '1'){
		url = BASE_PATH+"userinfo/custRechargeWithdrawController/findUserRechargePage.shtml";
		pageElId = 'pay_index_recharge_page';
	}else if(flag == '2'){
		url = BASE_PATH+"userinfo/custRechargeWithdrawController/findUserWithdrawPage.shtml";
		pageElId = 'pay_index_withdrawa_page';
	}
	var params;
	if(elId!=null && elId != '' ){
		params = jqueryUtil.serializeObject($("#"+elId),true);
		if(flag == '1'){
			//101：线下充值、103：线上充值、202：提现记录、302：投资成功、401：加息奖励、403：投资奖励、405：推荐奖励、407：收取本金、409：收取利息、411：收取罚息、413：债权转让、415：红包、419：积分兑现、421：系统奖励、423：利息管理费分、425：收款   、502：利息管理费 、503：借款成功、504：还款  、506：VIP付费 、508：提现手续费 、510：已还本金、512：已还利息、514：支付逾期罚息,516：债权转让服务,518：购买债权、520：账户管理费 、521：续投奖励、
			params.businessTypeStr = "101,103";
		}else if(flag == '2'){
			params.businessTypeStr = "202";
		}
		$("#"+pageElId).page( 'destroy' );
	}
	initPage(url,flag,params,pageElId);
}

/*初始化分页*/
function initPage(url,flag,params,pageElId){
	/*分页*/
	$("#"+pageElId).page({
		pageSize : 10,    // 测试设置为1
		showInfo : false,
		showJump : false,
		showPageSizes : false,
		loadFirstPage : true,  // 自动加载第一页
		remote : {
			url : url,
			params : params, //条件查询
			success : function(result, pageIndex) {
				if(result.message.status == 200 ){
		    		if(flag == '1'){
		    			// 持有债权
		    			htmlRechargeList(result.vos);
		    		}else if(flag == '2'){
		    			// 转让中债权
		    			htmlWithdrawList(result.vos);
		    		}
		    	}
			},
			pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
			pageSizeName : 'pageSize'//请求参数，每页数量
		}
	});
}

/**
 * 充值列表
 * @param vos
 */
function htmlRechargeList(vos){
	var html = "";
	if(vos != null && vos.length>0){
		for(var i=0;i<vos.length;i++){
			html += '<tr>'+
				 '<td class="pl30"> '+vos[i].recTime+ '</td>'+ 
				 '<td class="colororg"> '+vos[i].recOrderNo+' </td>'+ 
				 '<td> '+common.formatCurrency(vos[i].amount)+'  </td>'+
				 '<td> '+vos[i].payName+'</td>'+
				 '<td> '+vos[i].recStatus+' </td>'+ 
				 '</tr> ';
		}
	}
	$("#pay_index_recharge_tbody").html(html);
}

/**
 * 提现列表
 * @param vos
 */
function htmlWithdrawList(vos){
	var html = "";
	if(vos != null && vos.length>0){
		for(var i=0;i<vos.length;i++){
			html += '<tr>'+
			 '<td class="pl30"> '+vos[i].applyTime+ '</td>'+ 
			 '<td class="colororg"> '+vos[i].recOrderNo+' </td>'+ 
			 '<td> '+common.formatCurrency(vos[i].amount)+'  </td>'+
			 '<td> '+common.formatCurrency(vos[i].fee)+'  </td>'+
			 '<td> '+vos[i].bankcardNo+'</td>'+
			 '<td> '+vos[i].auditStatus+' </td>'+ 
			 '<td> '+vos[i].witDesc+' </td>'+
			 '</tr> ';
		}
	}
	$("#pay_index_withdrawa_tbody").html(html);
}
/**
 * 打开添加银行卡页面
 */
function openAddBankCard(){
	//校验是否实名认证过
	$.ajax({
		type: "POST",
    	url : BASE_PATH+"/securityCenter/bankController/validateIsAttestation.shtml",
    	data:{"":""},
		dataType: "json",
		async:false, 
	    success: function(data){
	    	if(data.message.status ==200){
	    		if(data.count >0){
	    			//iframe层
	    			layer.open({
	    			    type: 2,
	    			    title: '',
	    			    shadeClose: true,
	    			    shade: 0.8,
	    			    offset: ['10%', '30%'],
	    			    area: ['700px', '48%'],
	    			    content: BASE_PATH+'securityCenter/bankController/toAddBankCardPage.shtml'
	    			});
	    		}else{
	    			layer.msg('实名认证后才能添加银行卡!', {icon: 5});
	    		}
	    		
	    	}
	    }
	});
}

/**
 * 点击银行卡
 */
function clickBank(){
	var bankCount = $("#bankCount").val();
	//根据当前客户是否有银行卡 进行跳转判断
	if(bankCount && bankCount>0){
		$("#withdraw_div").empty();
		//跳转到银行卡列表
		$("#withdraw_div").load( BASE_PATH+'userinfo/custRechargeWithdrawController/toBankList.shtml');
	}else{
		//跳转到添加银行卡页面
		openAddBankCard();
	}
}

/**
 * 点击充值
 */
function clickRecharge(){
	$("#withdraw_div").empty();
	$("#withdraw_div").load( BASE_PATH+'recharge/userRechargeController/toRecharge.shtml');
}

/**
 * 点击提现
 */
function clickWithdraw(){
	$("#withdraw_div").empty();
	$("#withdraw_div").load( BASE_PATH+'userinfo/custRechargeWithdrawController/toWithdrawPage.shtml');
}

/**
 * 打开 查看提现记录
 */
function queryWithdrawaClick(openStatus){
	location.replace( BASE_PATH+"/userinfo/custRechargeWithdrawController/toPayIndex.shtml?openStatus="+openStatus);
	//location.reload(false);
}

$(function(){
	//添加银行卡界面打开
    $(".tis_yin").click(function(){
    	$(".tis_yin").eq(1).removeClass("none");
    });
    
    //切换模块调用,tis_qie_cs
    qie(".tis_qie_ta",".tis_qie_cs","ms_t_se");
    
    loadData(1);
    loadData(2);
    
    if($("#openStatus").val()=="2"){
    	$(".tis_qie_ta").eq(1).click();
    }
});
