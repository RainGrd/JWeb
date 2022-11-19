<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/report/model/overdueRate_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/report/overdue_rate_report.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<br>
		<div>
			总还款期数： <label >${sumReapymentPlanNum }</label>   逾期数： <label >${sumOverdunNum }</label>   总逾期率：<label >${sumOverdunRate }</label> %
		</div>
		<br>
		<div class="showDataListWrap" id="overdueRateGrid">
			<table id="overdueRateGrid"></table>
		</div>
	</div>
	
	
	<script type="text/javascript">
		$(function(){
			overdueRate.initOverdueRateGrid();
		})
	</script>
</body>

