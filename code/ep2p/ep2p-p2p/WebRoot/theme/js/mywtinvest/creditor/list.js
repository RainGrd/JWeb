var Transfer = {
		// 初始化数据
	initData:function(){
		var url = BASE_PATH+"mybids/transferController/index/findTransferListData.shtml";
		initPage(url,null);
	},
	// 条件查询
	queryData:function(){
		
		var url = BASE_PATH+"mybids/transferController/index/findTransferListData.shtml";
		$("#page").page('destroy');
		initPage(url,$("#conditionForm").serializeArray());
	}
}


function setData(vos){
	if(vos == null || vos.length == 0){
		$("#transferList").html("");
		return;
	}
	var html = "";
	for(var i=0;i<vos.length;i++){
		var op = '<td><a class="btn_samll_gray" href="javascript:void(0)" onclick="toTransferInfo(\''+vos[i].transferId+'\')">已转让</a></td>';
		if(vos[i].transferStatus== '1'){
			op = '<td><a class="btn_samll" href="javascript:void(0)" onclick="toTransferInfo(\''+vos[i].transferId+'\')">立即购买</a></td>';
		}
		
		var yearRate = vos[i].yearRate==null?0:parseFloat(vos[i].yearRate*100);
		var successAmount = vos[i].successAmount==null?0.00:accounting.formatNumber(vos[i].successAmount,2);
		var projectValue = vos[i].projectValue==null?0.00:accounting.formatNumber(vos[i].projectValue,2);
		
		html +='<tr>'+
		      '<td class="colororg">'+yearRate+'%</td>'+
	          '<td>'+vos[i].transferCode+vos[i].borrowName+'</td>'+
	          '<td>'+vos[i].timeRemaining+'个月</td>'+
	          '<td>￥'+projectValue+'</td>'+
	          '<td>￥'+successAmount+'</td>'+
	          op+
	        '</tr>';
	
	}
	$("#transferList").html(html);
}


$(document).ready(function(){
	// 初始化债权购买数据
	Transfer.initData();
	
	/*绑定样式事件*/
	$("#proul li").click(function(){
		$(this).siblings().removeClass("sec_li");
		$(this).addClass("sec_li");
		
	});
	$("#yrul li").click(function(){
		$(this).siblings().removeClass("sec_li");
		$(this).addClass("sec_li");
		
	});
});




/*初始化分页*/
function  initPage(url,params){
	/*分页*/
	$("#page").page({
		pageSize : 10,    // 测试设置为1
		showInfo : false,
		showJump : false,
		showPageSizes : false,
		loadFirstPage : true,
		remote : {
			url : url,
			params : params,
			success : function(result, pageIndex) {
				setData(result.vos);
			},
			pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
			pageSizeName : 'pageSize'//请求参数，每页数量
		}
	});
}

/*跳转到债权详情页面*/
function toTransferInfo(pid){
	window.location.href = BASE_PATH+"mybids/transferController/index/toTransferInfo.shtml?pid="+pid;
}

/*设置查询条件值*/
function setFormValue(startYearRate,endYearRate){
	$("#startSuccessAmount").val(startYearRate);
	$("#endSuccessAmount").val(endYearRate);
	Transfer.queryData();
}

function setStatusToForm(value1,value2){
	$("#startTimeRemaining").val(value1);
	$("#endTimeRemaining").val(value2);
	Transfer.queryData();
}