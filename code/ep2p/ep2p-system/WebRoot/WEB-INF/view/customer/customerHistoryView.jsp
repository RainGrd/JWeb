<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/customerHistoryView.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/customerHistoryViewModel.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar">
			<form id="baseInfo" name="baseInfo" method="POST">
			<%@ include file="../common/customer_details.jsp" %>
			</form>
		</div>
		
		<div class="showDataListWrap">
			<table id="showCusGrids"></table>
		</div>
	</div> 
<script type="text/javascript">
	$(document).ready(function(){
		cusHistoryView.loadData();
		
		cusHistoryView.initDataGrid();
	});
</script>
	
</body>