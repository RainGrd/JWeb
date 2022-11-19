<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp"%>
<%@include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var borrowId = "${borrowId}";
</script>
<script type="text/javascript"
	src="<%=basePath%>theme/js/personcenter/myinvestment/projectdetail/projectinfo.js"></script>
<!-- 全部项目明细 -->
<div class="sidebar_b fl ">
	<div class="fl title_div">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">全部项目明细</span>
		</div>
		<div class="find_area fl">
			<form id="conditionForm">
			<img src="<%=basePath%>theme/default/images/dateicon.png" width="30" height="29" alt="日期选择" />
			<input type="text" name="startExpireTime" readonly="readonly" onFocus="WdatePicker()" /><i class="i_ ml5 mr5">-</i><input type="text" name="endExpireTime" readonly="readonly" onFocus="WdatePicker()" />
			<a class="btn_samll" href="javascript:InvestInfo.queryData()">查询</a>
			</form>
		</div>

	</div>
	<div class="invest_table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="esc_t">
			<thead>
				<tr>
					<th width="15%">待收时间</th>
					<th width="15%">编号</th>
					<th width="15%">借款名称</th>
					<th width="15%">期次</th>
					<th width="15%">待收本息</th>
					<th width="15%">状态</th>
				</tr>
			</thead>
			<tbody id="investInfoList">


			</tbody>
		</table>
		<!-- 分页 -->
		<div id="page" class="m-pagination  page_div fr"></div>
	</div>
	<div class="clearfix"></div>
</div>
