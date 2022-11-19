/**
 * e计划列表页面 js
 */
var BorrowDetail = {
	// 初始化数据
	initData : function() {
		var url = BASE_PATH
				+ "business/financialProductsManageController/index/selectFinProdData.shtml";
		initPage(url, null);

	},
	// 条件查询
	queryData : function() {
		var url = BASE_PATH
				+ "business/financialProductsManageController/index/selectFinProdData.shtml";
		$("#page").page('destroy');
		initPage(url, $("#conditionForm").serializeArray());
	}
}
/* 设置查询条件值 */
function setFormValue(startValue, endValue, type, obj) {
	if (type == null || type.trim().length == 0) {
		layer.alert('系统错误请联系管理员！');
		return;
	}
	if ("borDeadline" == type) {
		$("#borDeadlineMin").val(startValue);
		$("#borDeadlineMax").val(endValue);
	} else if ("borrowRate" == type) {
		$("#borrowRateMin").val(startValue);
		$("#borrowRateMax").val(endValue);
	} else if ("borStatus" == type) {
		$("#borStatus").val(startValue);
	}
	$(obj).siblings("i").removeClass("dr_sx_i").addClass("dr_sx_i_");
	// $(obj).siblings().css("color","#2980b9");
	$(obj).removeClass("dr_sx_i_").addClass("dr_sx_i");
	// $(obj).css("color","#FFF");
	BorrowDetail.queryData();
}

// 点击立即加入方法
function selectFinProdInfoData(borrowId) {
	window.location.href = BASE_PATH
			+ "business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId="
			+ borrowId;

}
function setData(borrowList) {
	$("#brrowDetailList").html("");
	if (borrowList == null || borrowList.length == 0) {
		return;
	}
	var html = "";
	for (var i = 0; i < borrowList.length; i++) {
		var borrowProgress = borrowList[i].borrowProgress == null ? 0
				: (borrowList[i].borrowProgress * 100).toFixed(0);
		var surplusMoney = borrowList[i].surplusMoney == null ? borrowList[i].borrowMoney
				: borrowList[i].surplusMoney;
		html += "<li>" + "	<p class='size20 colorDarkBlue'>"
				+ borrowList[i].borrowName
				+ "</p>"
				+ "	<p class='colorc size26 lineHeight62'><span class='size62'>"
				+ escfutil.formatCurrency(borrowList[i].borrowRate * 100)
				+ "</span><i class='size26'>%</i></p>"
				+ "     <p class='size16 colorDarkBlue'>计划期限"
				+ borrowList[i].borDeadline
				+ "个月</p>"
				+ "    <div class='dropSchedule'>"
				+ "		<dl class='barbox'>"
				+ " 			<dd class='barline' style='width: 220px;'>"
				+ "    			<div divindex='3' id='chartSlide_3' w='80' style='width:"
				+ borrowProgress
				+ "%;' class='charts baryellow '></div>"
				+ "			</dd>"
				+ "		</dl>"
				+ "        <p class='dropIComplete size12'><span class='colorDarkBlue'>"
				+ borrowProgress
				+ "%</span>已完成</p>"
				+ "        <p class='dropISurplus size12'><span class='colorDarkBlue'>￥"
				+ surplusMoney + "</span>剩余</p>" + "    </div>";
		// 已满标 还款中 逾期还款 预期垫付 显示 还款中
		if (borrowList[i].borStatus == 4 || borrowList[i].borStatus == 5
				|| borrowList[i].borStatus == 6 || borrowList[i].borStatus == 7
				|| borrowList[i].borStatus == 8) {
			html += "<a class='btn_gray dropJionBtn width170' href='javascript:void(0)' onclick=selectFinProdInfoData('"
					+ borrowList[i].pid + "')>已结束</a></p>";
		} else if (borrowList[i].borStatus == 1
				&& borrowList[i].publishTime != null) {
			// 倒计时
			html += "<a class='btn dropJionBtn width170' href='javascript:void(0)' id='"
					+ borrowList[i].pid
					+ "' onclick=selectFinProdInfoData('"
					+ borrowList[i].pid
					+ "')>"
					+ timer(borrowList[i].pid, borrowList[i].publishTime)
					+ "</a>";
		} else {
			html += "<a class='btn dropJionBtn width170' href='javascript:void(0)' onclick=selectFinProdInfoData('"
					+ borrowList[i].pid + "')>立即投资</a></p>";
		}
		html += "</li> ";
	}
	$("#brrowDetailList").html(html);
}

$(function() {
	// 初始化债权购买数据
	BorrowDetail.initData();
	// 加载e计划投资专题页 广告
	initEPlanBanner();
});
// 加载e计划投资专题页 广告
function initEPlanBanner() {
	$
			.ajax({
				type : "POST",
				url : BASE_PATH
						+ "/business/financialProductsManageController/index/initAdvBanner.shtml",
				data : {
					webTag : "ep2p_adv_finProdPagePage_t_1"
				},
				dataType : "json",
				success : function(data) {
					if (data.advList.length > 0) {
						var html = "<ul class='slides'>";
						for (var i = 0; i < data.advList.length; i++) {
							html += '<li> '+
	 									'<a title="" target="_blank" href="'+data.advList[i].url+'">'+
	 										'<img height="310" alt="" src="'+BASE_PATH+data.advList[i].fileUrl+'"> '+
										'</a> ' +
									'</li>';
						}
						html += "</ul>";
						if (data.advList.length > 1) {
							html += '<ul class="flex-direction-nav">'
									+ '<li><a class="flex-prev" href="javascript:void(0);">Previous</a></li>'
									+ '<li><a class="flex-next" href="javascript:void(0);">Next</a></li>'
									+ '</ul>'
									+ '<ol id="bannerCtrl" class="flex-control-nav flex-control-paging">'
									+ '<li><a>1</a></li>' + '<li><a>2</a></li>'
									+ '<li><a>2</a></li>' + '</ol>';
						}
						$("#banner_tabs").html(html);
					}else{
						//如果没有广告 广告位高度为0
						$("#banner_tabs").attr("height","0px");
					}
				},
				error : function() {
					layer.alert("");
				}
			});
}
/* 初始化分页 */
function initPage(url, params) {
	/* 分页 */
	$("#page").page({
		pageSize : 12, // 测试设置为1
		showInfo : false,
		showJump : false,
		showPageSizes : false,
		loadFirstPage : true, // 自动加载第一页
		remote : {
			url : url,
			params : params,
			success : function(result, pageIndex) {
				setData(result.borrowList);
			},
			pageIndexName : 'pageIndex', // 请求参数，当前页数，索引从0开始
			pageSizeName : 'pageSize'// 请求参数，每页数量
		}
	});
}

function timer(contentId, publishTime) {
	var ts = new Date(publishTime) - new Date();
	var tempStr1 = contentId.substring(0, contentId.lastIndexOf("!"));
	var tempStr2 = contentId.substring(contentId.lastIndexOf("!"));
	var contentId = tempStr1 + "\\" + tempStr2;
	if (ts > 0) {
		var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);// 计算剩余的天数
		var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);// 计算剩余的小时数
		var mm = parseInt(ts / 1000 / 60 % 60, 10);// 计算剩余的分钟数
		var ss = parseInt(ts / 1000 % 60, 10);// 计算剩余的秒数
		dd = checkTime(dd);
		hh = checkTime(hh);
		mm = checkTime(mm);
		ss = checkTime(ss);
		setTimeout("timer('" + contentId + "','" + publishTime + "')", 1000);
		$("#" + contentId).text(dd + "天" + hh + "时" + mm + "分" + ss + "秒");
		return dd + "天" + hh + "时" + mm + "分" + ss + "秒";
	}
	$("#" + contentId).text("立即投资");
	$("#" + contentId).removeClass();
	$("#" + contentId).addClass("btn dropJionBtn width170");
	$("#" + contentId).attr("onclick",
			"selectFinProdInfoData('" + contentId + "')");
	clearTimeout(timer);
	return "立即投资";

}
function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}
