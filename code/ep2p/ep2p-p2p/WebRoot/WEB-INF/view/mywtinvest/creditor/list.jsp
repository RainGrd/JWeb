<div class="dr_2">
	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@include file="/common/config.jsp"%>
	<%@include file="/common/taglibs.jsp"%>
	<script type="text/javascript">
		$(function() {
			// 加载e计划投资专题页 广告
			initEmortgageBanner();
		});
		//加载e计划投资专题页 广告
		function initEmortgageBanner() {
			$.ajax({
						type : "POST",
						url : BASE_PATH
								+ "/business/financialProductsManageController/index/initAdvBanner.shtml",
						data : {
							webTag : "ep2p_adv_transferPage_t_1"
						},
						dataType : "json",
						success : function(data) {
							if (data.advList.length > 0) {
								var html = "<ul class='slides'>";
								for (var i = 0; i < data.advList.length; i++) {
									html += '<li> '
											+ '<a title="" target="_blank" href="'+data.advList[i].url+'">'
											+ '<img height="310" alt="" src="'+BASE_PATH+data.advList[i].fileUrl+'"> '
											+ '</a> ' + '</li>';
								}
								html += "</ul>";
								if (data.advList.length > 1) {
									html += '<ul class="flex-direction-nav">'
											+ '<li><a class="flex-prev" href="javascript:void(0);">Previous</a></li>'
											+ '<li><a class="flex-next" href="javascript:void(0);">Next</a></li>'
											+ '</ul>'
											+ '<ol id="bannerCtrl" class="flex-control-nav flex-control-paging">'
											+ '<li><a>1</a></li>'
											+ '<li><a>2</a></li>'
											+ '<li><a>2</a></li>' + '</ol>';
								}
								$("#banner_tabs").html(html);
							} else {
								//如果没有广告 广告位高度为0
								$("#banner_tabs").attr("height", "0px");
							}
						},
						error : function() {
							layer.alert("");
						}
					});
		}
	</script>
	<script type="text/javascript"
		src="<%=basePath%>theme/js/mywtinvest/creditor/list.js"></script>
	<div id="banner_tabs" class="flexslider">
		  
	</div>	
	<div class="w1140 m_auto">
		<div class="ge_ye_tilte  ">
			<span class="ge_ye_tilte_nav  "
				onclick="javascript:window.location.href='<%=basePath%>business/financialProductsManageController/index/toFinProdPage.shtml'">e计划</span><span
				class="  ge_ye_tilte_nav  "
				onclick="javascript:window.location.href='<%=basePath%>business/optionalInvestController/index/toOptionalInvestList.shtml'">e首房/e抵押</span><span
				class="  ge_ye_tilte_nav  ms_t_se  "
				onclick="javascript:window.location.href='<%=basePath%>mybids/transferController/index/toTransferList.shtml'">债权转让</span>
		</div>
	</div>
	<!-- 条件 -->
	<div class="qurey_condtion f-14">
		<div class="yrq">
			<div class="fl yr">转让价格：</div>
			<div class="fr">
				<ul id="yrul">
					<li class="sec_li" onclick="setFormValue('','');">全部</li>
					<li onclick="setFormValue('','10000');">1万元以下</li>
					<li onclick="setFormValue('10000','50000');">1万~5万</li>
					<li onclick="setFormValue('50000','100000');">5万~10万</li>
					<li onclick="setFormValue('100000','');">10万以上</li>
				</ul>
			</div>
		</div>
		<div class="yrq">
			<div class="fl yr">剩余期限：</div>
			<div class="fr">
				<ul id="proul">
					<li class="sec_li" onclick="setStatusToForm('','');">全部</li>
					<li onclick="setStatusToForm('','12');">12月以下</li>
					<li onclick="setStatusToForm('12','24');">12~24月</li>
					<li onclick="setStatusToForm('24','');">24月以上</li>
				</ul>
			</div>
		</div>
		<form id="conditionForm">
			<input type="hidden" id="startSuccessAmount"
				name="startSuccessAmount" /> <input type="hidden"
				id="endSuccessAmount" name="endSuccessAmount" /> <input
				type="hidden" id="startTimeRemaining" name="startTimeRemaining" />
			<input type="hidden" id="endTimeRemaining" name="endTimeRemaining" />
		</form>
	</div>
	<!--债权转让列表 -->
	<div class="transfer_table clearfix">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="esc_t nthChildtd1">
			<thead>
				<th width="13%">年化率</th>
				<th width="23%">债权项目</th>
				<th width="17%">剩余期限</th>
				<th width="17%">项目价值</th>
				<th width="19%">转让价格</th>
				<th width="11%">&nbsp;</th>
			</thead>
			<tbody id="transferList">


			</tbody>
		</table>
	</div>

	<div style="margin: 0 auto; width: 1140px">
		<div id="page" class="m-pagination "></div>
	</div>
</div>