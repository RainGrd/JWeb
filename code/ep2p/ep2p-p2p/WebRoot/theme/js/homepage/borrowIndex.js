//先加载 e计划 进行中 年化利率最高的标的
function selectEplanData() {
	$
			.ajax({
				type : "POST",
				url : BASE_PATH
						+ "business/financialProductsManageController/index/selectFinProdData.shtml",
				data : {
					"pageSize" : 3,
					"pageIndex" : 0
				},
				dataType : "json",
				success : function(data) {
					if (data.message.status == 200) {
						$("#eplanCount").text("更多(" + data.total + ")");
						loadEplanDataHtml(data.borrowList);
					}
				}
			});

}
// 首页e计划
function loadEplanDataHtml(borrowList) {
	if (borrowList != null || borrowList.length > 0) {
		var html = "";
		for (var i = 0; i < borrowList.length; i++) {
			// 剩余金额 如果剩金额没有设值 剩余金额等于 借款金额- 已投金额
			var surplusMoney = borrowList[i].surplusMoney == null ? borrowList[i].borrowMoney
					: borrowList[i].surplusMoney;
			var borrowProgress = borrowList[i].borrowProgress == undefined ? 0
					: (borrowList[i].borrowProgress * 100).toFixed(2);

			html += '<div class="plan_list_box">'
					+ '<div class="plan_list tc">'
					+ '  <p class="f-18 e_plan_title">'
					+ borrowList[i].borrowName
					+ '</p>'
					+ '  <p class="colororg e_percent_num"><span class="size62">'
					+ escfutil.formatCurrency(borrowList[i].borrowRate*100)
					+ '</span><span class="percentDot">%</span></p>'
					+ '  <p class="text_blue f-18">计划期限'
					+ borrowList[i].borDeadline
					+ '个月</p>'
					+ '  <div class="tc">'
					+ '   <dl class="barbox">'
					+ '      <dd class="barline">'
					+ '       <div divindex="3" id="chartSlide_3" w="80" style="width:'
					+ borrowProgress
					+ '%;" class="charts"></div>'
					+ '      </dd>'
					+ '   </dl>'
					+ '    <div class="number"> <span class="fr">￥'
					+ escfutil.formatCurrency(surplusMoney)
					+ '<font class="digital">剩余</font></span>'
					+ borrowProgress
					+ '%<font class="digital">已完成</font> </div>' + '  </div>';
			// 已满标
			if (borrowList[i].borStatus == 4) {
				html += "<p class='clear mt20'><a class='btn_samll_gray dropJionBtn width170 ' href='javascript:void(0)' onclick=selectFinProdInfoData('"+ borrowList[i].pid + "')>查看</a></p>";
			} else if (borrowList[i].borStatus == 1
					&& borrowList[i].publishTime != null) {
				// 倒计时
				html += "<p class='clear mt20'><a class='btn dropJionBtn width170' href='javascript:void(0)' onclick=selectFinProdInfoData('"
						+ borrowList[i].pid
						+ "') id='"
						+ borrowList[i].pid
						+ "' >"
						+ timer(borrowList[i].pid, borrowList[i].publishTime)
						+ "</a></p>";
			} else {
				html += '<p class="clear mt20"><a class="btn dropJionBtn width170" href="javascript:void(0)" onclick=selectFinProdInfoData("'
						+ borrowList[i].pid + '")>立即投资</a></p>';
			}
			html += '</div>' + '</div> ';
		}
		$("#eplanData").html(html);
	}

}

// e计划 点击立即加入方法
function selectFinProdInfoData(borrowId) {
	window.location = BASE_PATH
			+ "business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId="
			+ borrowId + "&pageTag=1";
}
function toFinProdPage() {
	window.location = BASE_PATH
			+ "/business/financialProductsManageController/index/toFinProdPage.shtml";
}
// 再加载e首房 e抵押数据
function selectEmortgageData() {
	$
			.ajax({
				type : "POST",
				url : BASE_PATH
						+ "business/optionalInvestController/index/selectOptionalInvestList.shtml",
				data : {
					"pageSize" : 8,
					"pageIndex" : 0
				},
				dataType : "json",
				success : function(data) {
					$("#eMortgageCount").text("更多(" + data.total + ")");
					loadEmortgageDataHtml(data.borrowList);
				}
			});

}
// 首页e首房 数据
function loadEmortgageDataHtml(borrowList) {
	if (borrowList != null || borrowList.length > 0) {
		var html = "";
		if (borrowList.length > 0) {
			html = '<thead >' + ' <th width="33%">项目名称</th>'
					+ ' <th width="16%">年化率</th>' + ' <th width="11%">金额</th>'
					+ ' <th width="14%">期限</th>' + ' <th width="10%">进度</th>'
					+ ' <th width="13%">&nbsp;</th>' + '</thead>';
		}
		for (var i = 0; i < borrowList.length; i++) {
			html += '<tr>'
					+ '<td class="pos-r">'
					+ (borrowList[i].borrowName == undefined ? ""
							: borrowList[i].borrowName);
			if (borrowList[i].investRewardScale != undefined) {
				html += '  <div class="jlin">奖励'
						+ escfutil.formatCurrency(borrowList[i].investRewardScale == undefined ? 0
								: borrowList[i].investRewardScale)
						+ '%</div></td>';
			}

			html += '<td class="colororg">'
					+ escfutil.formatCurrency(borrowList[i].borrowRate == undefined ? 0
							: borrowList[i].borrowRate*100)
					+ '%</td>'
					+ '<td>'
					+ escfutil.formatCurrency(borrowList[i].borrowMoney)
					+ '</td>'
					+ '<td>'
					+ borrowList[i].borDeadline
					+ '个月</td>'
					+ '<td>'
					+ escfutil.formatCurrency(borrowList[i].borrowProgress == undefined ? 0
							: borrowList[i].borrowProgress * 100) + '%</td>';
			if (borrowList[i].borStatus == 4 || borrowList[i].borStatus == 5) {
				html += '<td><a class="btn_samll_gray" href="javascript:void(0)" style="line-height: inherit;width: 56px;" onclick=selectEmortgage("'
						+ borrowList[i].pid + '") >还款中</a></td>';
			} else if (borrowList[i].borStatus == 1
					&& borrowList[i].publishTime != null) {
				// 倒计时
				html += '<td><a class="btn_samll" href="javascript:void(0)" style="line-height: inherit;" id="'
						+ borrowList[i].pid
						+ '" onclick=selectEmortgage("'
						+ borrowList[i].pid
						+ '")>'
						+ timerFormat(borrowList[i].pid,
								borrowList[i].publishTime) + '</a></td>';
			}else if (borrowList[i].borStatus == 8) {
				// 倒计时
				html += '<td><a class="btn_samll_gray" href="javascript:void(0)" style="line-height: inherit;width: 56px;" onclick=selectEmortgage("'
					+ borrowList[i].pid + '") >已结清</a></td>';
			} else {
				html += '<td><a class="btn_samll" href="javascript:void(0)" style="line-height: inherit;" onclick=selectEmortgage("'
						+ borrowList[i].pid + '") >立即投资</a></td>'
			}
			'</tr>';
		}
		$("#eMortgageData").html(html);
	}
}
// e首房 e抵押 点击立即加入方法
function selectEmortgage(borrowId) {
	window.location = BASE_PATH
			+ "business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId="
			+ borrowId + "&pageTag=1";
}
function toEmortgagePage() {
	window.location = BASE_PATH
			+ "/business/optionalInvestController/index/toOptionalInvestList.shtml";
}
/**
 * 年月日时分秒
 * 
 * @param contentId
 * @param publishTime
 * @returns {String}
 */
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
/**
 * 时分秒
 * 
 * @param contentId
 * @param publishTime
 * @returns {String}
 */
function timerFormat(contentId, publishTime) {
	var ts = new Date(publishTime) - new Date();
	var tempStr1 = contentId.substring(0, contentId.lastIndexOf("!"));
	var tempStr2 = contentId.substring(contentId.lastIndexOf("!"));
	var contentId = tempStr1 + "\\" + tempStr2;
	if (ts > 0) {
		var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);// 计算剩余的天数
		var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);// 计算剩余的小时数
		var mm = parseInt(ts / 1000 / 60 % 60, 10);// 计算剩余的分钟数
		var ss = parseInt(ts / 1000 % 60, 10);// 计算剩余的秒数
		// dd = checkTime(dd);
		// hh = checkTime(hh);
		mm = checkTime(mm);
		ss = checkTime(ss);
		hh = (24 * dd) + hh
		setTimeout("timerFormat('" + contentId + "','" + publishTime + "')",
				1000);
		$("#" + contentId).text(hh + ":" + mm + ":" + ss);
		return hh + ":" + mm + ":" + ss;
	}
	$("#" + contentId).text("立即投资");
	$("#" + contentId).removeClass();
	$("#" + contentId).addClass("btn dropJionBtn width170");
	$("#" + contentId).attr("onclick",
			"selectFinProdInfoData('" + contentId + "')");
	clearTimeout(timer);

}
function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}
