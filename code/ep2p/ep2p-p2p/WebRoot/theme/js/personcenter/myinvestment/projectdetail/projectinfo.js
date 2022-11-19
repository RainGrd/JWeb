var InvestInfo = {
	// 初始化数据
	initData : function() {
		var url = BASE_PATH
				+ "usercenter/borrowDetailController/findReceiptPlanListData.shtml?borrowId="+borrowId;
		initPage(url, null);

	},
	// 条件查询
	queryData : function() {
		var url = BASE_PATH
				+ "usercenter/borrowDetailController/findReceiptPlanListData.shtml?borrowId="+borrowId;
		var params = $("#conditionForm").serializeArray(); // 条件参数
		$("#page").page('destroy'); // 销毁分页
		initPage(url, params);
	},
	// 债权转让界面
	toTransferConfirm:function(pid){
		//债权转让界面
		window.location.href = BASE_PATH+"usercenter/borrowDetailController/findReceiptPlanById.shtml?pid="+pid;
	}
}
/*set 数据*/
function setData(vos,isTransfer,isVip) {
	var html = "";
	if (vos == null || vos.length == 0) {
		$("#investInfoList").html(html);
		return;
	}
	for (var i = 0; i < vos.length; i++) {
		var capital = accounting.formatNumber(vos[i].capital,2);
		var interest = accounting.formatNumber(vos[i].interest,2);
		var lateFee = accounting.formatNumber(vos[i].lateFee,2);
		var netHike = accounting.formatNumber(vos[i].netHike,2);
		var lateFeeDate = vos[i].lateFeeDate;
		var lf = '';
		var ld = '';
		var jx = '';
		if(vos[i].lateFee > 0){
			lf = '<p><span class="tipsSpan30">逾期赔付</span><span class="tipsSpan70">￥'+lateFee+'</span></p>';
			ld = '<p><span class="tipsSpan30">逾期</span><span class="tipsSpan70">'+lateFeeDate+'天</span></p>';
		}
		if(vos[i].netHike > 0){
			jx = '<p><span class="tipsSpan30">加息</span><span class="tipsSpan70">￥'+netHike+'</span></p>';
		}
		
		var principalAndInterest = accounting.formatNumber(vos[i].principalAndInterest,2);
		
		var url_1,url_2;
		if(vos[i].borrowType == '3'){
			// e计划
			url_1 = '<a href="'+basePath+'business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowCode+'</a>';
			url_2 = '<a href="'+basePath+'business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowName+'</a>';
		}else{
			// e首房，e抵押
			url_1 = '<a href="'+basePath+'business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowCode+'</a>';
			url_2 = '<a href="'+basePath+'business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId='+vos[i].borrowId+'">'+vos[i].borrowName+'</a>';
		}
		
		html += '<tr>' + '<td>' + vos[i].expireTime + '</td>'
				+ '<td class="colororg">' +url_1 + '</td>'
				+ '<td>' + url_2 + '</td>' + '<td>'
				+ vos[i].planIndex + '/' + vos[i].borDeadline + '</td>'
				 +'<td> <span class="tipsBtn">￥'+principalAndInterest
     			+'<div class="tipsWrap">'
                 +'<p><span class="bottom">￥'+principalAndInterest+'</span></p>'
                  +'<p class="background"><span class="tipsSpan30">类型</span><span class="tipsSpan70">金额</span></p>'
                  +'<p><span class="tipsSpan30">本金</span><span class="tipsSpan70">￥'+capital+'</span></p>'
                  +'<p><span class="tipsSpan30">利息</span><span class="tipsSpan70">￥'+interest+'</span></p>'
                  +jx+lf+ld
         		+'</div>'
     		+'</span></td>'
				+ '<td>'+ vos[i].receiptStatusName + '</td></tr>';
	
	}
	$("#investInfoList").html(html);
}



/* 初始化分页 */
function initPage(url, params) {
	/* 分页 */
	$("#page").page({
		pageSize : 10, // 测试设置为1
		showInfo : false,
		showJump : false,
		showPageSizes : false,
		loadFirstPage : true, // 自动加载第一页
		remote : {
			url : url,
			params : params,
			success : function(result, pageIndex) {
				setData(result.vos,result.isTransfer,result.isVip);
			},
			pageIndexName : 'pageIndex', // 请求参数，当前页数，索引从0开始
			pageSizeName : 'pageSize'// 请求参数，每页数量
		}
	});
}

$(document).ready(function() {
	// 初始化债权购买数据
	InvestInfo.initData();
});